package com.kapilkolte.CloudGateway.productservice.service;

import com.kapilkolte.CloudGateway.productservice.entity.Product;
import com.kapilkolte.CloudGateway.productservice.model.ProductRequest;
import com.kapilkolte.CloudGateway.productservice.model.ProductResponse;

import java.util.List;

public interface ProductService {
    long addProduct(ProductRequest productRequest);
    List<Product> findAll();
    ProductResponse findById(long productId);

    String Delete(long productId);

    long reduceQuantity(long productId, long quantity);
}
