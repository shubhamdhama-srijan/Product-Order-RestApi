package com.shubham.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product_table")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Product {
	
	@Id
	private Integer id;
	@Column(name = "ProductName")
	private String productName;
	@Column(name = "PartNumber")
	private Integer partNo;
	@Column(name = "ProductLabel")
	private String productLabel;
	

}
