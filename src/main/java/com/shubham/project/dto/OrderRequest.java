package com.shubham.project.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shubham.project.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class OrderRequest {

	private int ordId;
	
	private Product product;
	
	@NotBlank(message = "Order Title should not be Blank!!")
	@NotNull(message = "Order Title should not be null!!")
	private String orderTitle;
	
	@NotBlank(message = "Order Name should not be Blank!!")
	@NotNull(message = "Order Name should not be null!!")
	private String orderName;
	
	@NotNull(message = "Order Date should not be null!!")
	@FutureOrPresent
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate orderdDate;
	
	

	
}
