package com.shubham.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@GeneratedValue
	private int id;
	
	@Column(name = "ProductName")
	private String productName;
	
	@Column(name = "PartNumber")
	private String partNo;
	
	@Column(name = "ProductLabel")
	private String productLabel;
	
	@OneToMany(targetEntity = Order.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "po_fk",referencedColumnName = "id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Order> orders;
	
	@OneToMany(targetEntity = Purchase.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "pp_fk",referencedColumnName = "id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Purchase> purchases;
	

}
