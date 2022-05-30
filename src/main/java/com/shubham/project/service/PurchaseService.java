package com.shubham.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shubham.project.entity.Purchase;
import com.shubham.project.repository.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	public List<Purchase> getAllPurchases(){
		return purchaseRepository.findAll();
	}
	
	public Purchase createPurchase(Purchase purchase) {
		
		return purchaseRepository.save(purchase);
	}
	
    public Purchase getPurchaseById(int id) {
		
		return purchaseRepository.findById(id).get();

	}
    
    public Purchase updatePurchase(Purchase purchase) {
    	return purchaseRepository.save(purchase);
    }
    
    public void deletePurchase(int id) {
    	 purchaseRepository.deleteById(id);
    }

}
