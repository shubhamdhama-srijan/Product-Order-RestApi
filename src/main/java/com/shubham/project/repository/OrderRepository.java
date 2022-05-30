package com.shubham.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.shubham.project.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findAllOrdersByProductId(@Param("ordId")Integer id);
}
