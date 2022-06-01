package com.shubham.project.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.shubham.project.entity.Order;
import com.shubham.project.entity.Purchase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ProductRequest {
	
	private int id;
	@NotBlank(message = "Product Name should not be Blank!!")
	@NotNull(message = "Product Name should not be null!!")
	private String productName;
	
	@Pattern(regexp = "^\\d{6}$",message = "Invalid Part number number entered!!")
	private String partNo;
	
	@NotBlank(message = "Product Label should not be Blank!!")
	@NotNull(message = "Product Label should not be null!!")
	private String productLabel;
	
	private List<Order> orders;
	
	private List<Purchase> purchases;


}
