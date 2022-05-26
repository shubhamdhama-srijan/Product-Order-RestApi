package com.shubham.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.project.entity.Order;
import com.shubham.project.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	//To get all the orders
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	//To create a new order
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	//To update a order
	public Order updateProduct(Order order) {
		return orderRepository.save(order);
	}

	//To delete a order by orderId
	public void deleteOrder(int id) {
		orderRepository.deleteById(id);
	}

	//To get order by id
	public Optional<Order> getOrderByOrdId(int id) {
		return orderRepository.findById(id);
	}
	
	
}
