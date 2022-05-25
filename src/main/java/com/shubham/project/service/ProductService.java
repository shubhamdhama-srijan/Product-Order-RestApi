package com.shubham.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.shubham.project.entity.Product;
import com.shubham.project.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
    public List<Product> getAllProducts(){
    	return productRepository.findAll();
    }
    
    public Product createProduct(Product product) {
    	return productRepository.save(product);
    	
    }
    
    public Product getProductById(int id) {
		
		return productRepository.findById(id).get();

	}

}
