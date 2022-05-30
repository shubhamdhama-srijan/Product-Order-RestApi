
package com.shubham.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.project.dto.OrderRequest;
import com.shubham.project.entity.Order;
import com.shubham.project.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
		
	}
	
	public Order createOrder(Order order) {
    	return orderRepository.save(order);
    	
    }
    
    public Order getOrderById(int id) {
		
		return orderRepository.findById(id).get();
		
	}
    
	public Order updateOrder(Order order) {
		
		return orderRepository.save(order);

	}
	
	public void deleteOrder(int id) {
		
	    orderRepository.deleteById(id);
	}
	
//	public List<Order> getOrderByProductId(){
//		
//	}
	
	

}
