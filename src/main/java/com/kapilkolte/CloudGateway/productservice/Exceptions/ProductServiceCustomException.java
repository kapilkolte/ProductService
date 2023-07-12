package com.kapilkolte.CloudGateway.productservice.Exceptions;

import lombok.Data;

@Data
public class ProductServiceCustomException  extends  RuntimeException {

    private  String errorCode;

    private String errorMessage;
    public ProductServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorMessage = message;
        this.errorCode = errorCode;
    }
}
