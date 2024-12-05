package com.marius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.marius")
public class ProductManagement {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagement.class, args);
	}
}
