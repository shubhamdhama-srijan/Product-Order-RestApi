package com.shubham.project.entity;

import java.time.LocalDate;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Order_table")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue
//	@Column(name = "OrderId")
	private int ordId;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Product product;
	
//	@Column(name = "OrderTitle")
	
//	@NotNull(message = "Order Title should not be null!!")
//	@NotBlank(message = "Order Title should not be Blank!!")
	private String orderTitle;
	
//	@Column(name = "OrderName")
	
//	@NotNull(message = "Order Name should not be null!!")
//	@NotBlank(message = "Order Name should not be Blank!!")
	private String orderName;
	

//	@NotNull(message = "Order Date should not be null!!")
//	@FutureOrPresent
//	@JsonFormat(pattern = "yyyy-mm-dd", lenient = OptBoolean.FALSE)
//	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate orderdDate;

}
