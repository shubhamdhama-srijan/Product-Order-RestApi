package com.shubham.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.project.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {

}
