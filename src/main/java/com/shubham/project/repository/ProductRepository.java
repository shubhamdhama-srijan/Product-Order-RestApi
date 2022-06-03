package com.shubham.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	boolean existsById(Integer id);
}
