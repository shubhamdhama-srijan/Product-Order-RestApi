package com.shubham.project.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shubham.project.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class PurchaseRequest {
	
	private int purchId;
	
	@NotNull(message = "purchase Date should not be null!!")
	@FutureOrPresent
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate purchDate;
	
	private Product product;

}
