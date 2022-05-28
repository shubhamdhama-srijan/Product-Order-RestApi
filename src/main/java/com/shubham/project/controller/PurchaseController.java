package com.shubham.project.controller;

import java.util.List;

import javax.validation.constraints.Positive;

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

import com.shubham.project.entity.Product;
import com.shubham.project.entity.Purchase;
import com.shubham.project.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@PostMapping
	public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase){
		return new ResponseEntity<>(purchaseService.createPurchase(purchase),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Purchase>> getAllPurchases(){
		return ResponseEntity.ok(purchaseService.getAllPurchases());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPurchaseById(@PathVariable int id){
		Purchase purchase = purchaseService.getPurchaseById(id);
		return new ResponseEntity<Purchase>(purchase,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public Purchase updatePurchase(@PathVariable int id, @RequestBody Purchase purchase){
		purchase.setPurchId(id);
		return purchaseService.updatePurchase(purchase);
		
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable int id) {
    	purchaseService.deletePurchase(id);
    	return  ResponseEntity.ok().body("Deleted Successfully");
    }

}
