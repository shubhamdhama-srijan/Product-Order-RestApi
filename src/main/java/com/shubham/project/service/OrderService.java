
package com.shubham.project.service;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shubham.project.dto.OrderRequest;
import com.shubham.project.entity.Order;
import com.shubham.project.entity.Product;
import com.shubham.project.repository.OrderRepository;
import com.shubham.project.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
		
	}
	
	public Order createOrder(OrderRequest orderRequest) {
		Order order = Order.build(0,
				orderRequest.getProduct(),
				orderRequest.getOrderTitle(),
				orderRequest.getOrderName(), orderRequest.getOrderdDate());
    	return orderRepository.save(order);
    	
    }
    
    public Order getOrderById(int id) {
		
		return orderRepository.findById(id).get();
		
	}
    
	public Order updateOrder(OrderRequest orderRequest) {
		Order order = Order.build(orderRequest.getOrdId(),
				orderRequest.getProduct(),
				orderRequest.getOrderTitle(),
				orderRequest.getOrderName(),
				orderRequest.getOrderdDate()
				);
		return orderRepository.save(order);

	}
	
	public void deleteOrder(int id){
		
	    	orderRepository.deleteById(id);
	}
	
	public List<Order> getOrderByProductId(int id){
		
		return orderRepository.findAllOrdersByProductId(id);
		
	}
	
	

}
