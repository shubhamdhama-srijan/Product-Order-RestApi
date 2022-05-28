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
	
	
//	to get all products
    public List<Product> getAllProducts(){
    	return productRepository.findAll();
    }
    
    
//  to create product
    public Product createProduct(ProductRequest productRequest) {
    	
//    	Product product = Product.build(0, productRequest.getProductName(), 
//    			productRequest.getPartNo(),
//    			productRequest.getProductLabel(),
//    			productRequest.getOrders(),
//    			productRequest.ge);   
    	
    	Product product = Product.build(0, productRequest.getProductName(),
    			productRequest.getPartNo(),
    			productRequest.getProductLabel(),
    			productRequest.getOrders(),
    			productRequest.getPurchases());
    	return productRepository.save(product);
    	
    }
    
    
//   get product by id 
    public Product getProductById(int id) {
		
		return productRepository.findById(id).get();

	}
    
    
//   update product 
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);

	}
	
	
//	delete product by id
	public void deleteProduct(int id) {
		
	    productRepository.deleteById(id);
	}

}
