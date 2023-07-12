package com.kapilkolte.CloudGateway.productservice;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductserviceApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(ProductserviceApplication.class, args);
	}

}
