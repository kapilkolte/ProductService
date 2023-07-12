package com.kapilkolte.CloudGateway.productservice.service;

import com.kapilkolte.CloudGateway.productservice.entity.Product;
import com.kapilkolte.CloudGateway.productservice.model.ProductRequest;
import com.kapilkolte.CloudGateway.productservice.repository.ProductRepository;
import com.kapilkolte.CloudGateway.productservice.Exceptions.ProductServiceCustomException;
import com.kapilkolte.CloudGateway.productservice.model.ProductResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class ProductServiceImpl implements  ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("::**************** ProductServiceImpl::addProduct method called.************::");
        log.info("Adding product...");

        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
            productRepository.save(product);

        log.info("Product created.............");
        log.info("::**************** ProductServiceImpl::addProduct method executed.************::");
         return  product.getProductId();
    }

    @Override
    public List<Product> findAll() {
        log.info("::**************** ProductServiceImpl::findAll method called and executed.************::");
        return  productRepository.findAll();
    }

    @Override
    public ProductResponse findById(long productId) {
        log.info("::**************** ProductServiceImpl::findById method called************::");
        log.info("searching product with id " + productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("product with given id "+productId+" is not found", "PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        log.info("Get the product with product id "+ productResponse);
        log.info("::**************** ProductServiceImpl::findById method executed.************::");
        return productResponse;
    }

    @Override
    public String Delete(long productId) {
        log.info("::**************** ProductServiceImpl::Delete method called.************::");
        Product product = productRepository.findById(productId)
                .stream()
                .findFirst()
                .get();

        productRepository.delete(product);
        log.info("::**************** ProductServiceImpl::Delete method executed successfully.************::");
        return "Product with Id "+productId+ "is deleted successfuly";
    }

    @Override
    public long reduceQuantity(long productId, long quantity) {
        log.info("::**************** ProductServiceImpl::reduceQuantity method called.************::");
        log.info("product quantity to be reduced for productId "+productId+" and quantity "+ quantity);
        Product product =  productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with give id is not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY");
        }
        long reducedProductCount = product.getQuantity() - quantity;
        product.setQuantity(reducedProductCount);
        productRepository.save(product);
        log.info("Product quantity reduced successfully");
        log.info("::**************** ProductServiceImpl::reduceQuantity method executed.************::");
        return reducedProductCount;

    }
}
