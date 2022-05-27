package com.shubham.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.project.dto.ProductRequest;
import com.shubham.project.entity.Product;
import com.shubham.project.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
    public List<Product> getAllProducts(){
    	return productRepository.findAll();
    }
    
 public Product createProduct(ProductRequest productRequest) {
    	
    	Product product = Product.build(0, productRequest.getProductName(), 
    			productRequest.getPartNo(),
    			productRequest.getProductLabel(),
    			productRequest.getOrders());    	
    	return productRepository.save(product);
    	
    }
    
    public Product getProductById(int id) {
		
		return productRepository.findById(id).get();

	}
    
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);

	}
	
	public void deleteProduct(int id) {
		
	    productRepository.deleteById(id);
	}

}
