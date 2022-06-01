package com.shubham.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.project.dto.ProductRequest;
import com.shubham.project.entity.Product;
import com.shubham.project.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id){
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}
    
    @PutMapping("/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody @Valid ProductRequest productRequest){
		productRequest.setId(id);
		return productService.updateProduct(productRequest);
		
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
    	productService.deleteProduct(id);
    	return  ResponseEntity.ok().body("Deleted Successfully");
    }

}
