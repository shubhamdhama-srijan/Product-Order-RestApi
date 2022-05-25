package com.shubham.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.project.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
