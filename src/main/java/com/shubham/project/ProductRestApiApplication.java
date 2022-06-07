package com.shubham.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.shubham.project"})public class ProductRestApiApplication {

	public static void main(String[] args) {
		ApplicationContext ac =SpringApplication.run(ProductRestApiApplication.class, args);
	}

}
