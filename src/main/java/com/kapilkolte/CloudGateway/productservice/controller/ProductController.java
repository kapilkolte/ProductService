package com.kapilkolte.CloudGateway.productservice.controller;

import com.kapilkolte.CloudGateway.productservice.entity.Product;
import com.kapilkolte.CloudGateway.productservice.model.ProductRequest;
import com.kapilkolte.CloudGateway.productservice.model.ProductResponse;
import com.kapilkolte.CloudGateway.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> GetAllProduct() {
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable long productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK) ;
    }
    @PostMapping
    public ResponseEntity<Long> Save(@RequestBody ProductRequest productRequest) {
        Long productId =  productService.addProduct(productRequest);
        return  new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable long productId) {
        return  productService.Delete(productId);
    }

    @PutMapping("/reduceQuantity/{productId}")
    public ResponseEntity<String> reduceQuantity(
            @PathVariable("productId") long productId,
            @RequestParam long quantity
    ) {
       long reducedProductCount = productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>("Quantity of product with productId = "+productId+ " is reduced by "+ quantity,HttpStatus.OK);
    }
}
