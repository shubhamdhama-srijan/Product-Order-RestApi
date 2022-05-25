package com.shubham.project.web;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.book.model.Book;
import com.shubham.project.entity.Product;
import com.shubham.project.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/signup")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Product>> getAllUsers(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id){
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}

}
