package com.shubham.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Order_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	@Id
//	@Column(name = "OrderId")
	private int ordId;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Product product;
	
//	@Column(name = "OrderTitle")
	private String orderTitle;
	
//	@Column(name = "OrderName")
	private String orderName;
	
//	@Column(name = "OrderDate")
	private Date orderdDate;

}
