package com.shubham.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
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

import com.shubham.project.dto.PurchaseRequest;
import com.shubham.project.entity.Order;
import com.shubham.project.entity.Product;
import com.shubham.project.entity.Purchase;
import com.shubham.project.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@PostMapping
	public ResponseEntity<Purchase> createPurchase(@RequestBody @Valid PurchaseRequest purchaseRequest){
		return new ResponseEntity<>(purchaseService.createPurchase(purchaseRequest),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<?>> getAllPurchases(){
		List<Purchase> purchList = purchaseService.getAllPurchases();
		List<String> msgList = new ArrayList<String>();
		msgList.add("empty");
		if(purchList==null) {
	
			return ResponseEntity.ok().body(msgList);
		}
		return ResponseEntity.ok(purchaseService.getAllPurchases());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPurchaseById(@PathVariable int id){
		Purchase purchase = purchaseService.getPurchaseById(id);
		return new ResponseEntity<Purchase>(purchase,HttpStatus.FOUND);
	}
	
	@PutMapping("/{id}")
	public Purchase updatePurchase(@PathVariable int id, @RequestBody @Valid PurchaseRequest purchaseRequest){
		purchaseRequest.setPurchId(id);
		return purchaseService.updatePurchase(purchaseRequest);
		
	}
    
//	delete a purchase by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable int id) {
    	purchaseService.deletePurchase(id);
    	return  ResponseEntity.ok().body("Deleted Successfully");
    }

}
