package com.kapilkolte.CloudGateway.productservice.model;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
public class ProductRequest {
    private String productName;
    private long price;
    private long quantity;
}
