package com.shubham.project.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Purchase_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

	@Id
	@GeneratedValue
	private int purchId;
	
//	@Column(name = "purchaseDate")
//	@NotNull(message = "Order Date should not be null!!")
//	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate purchDate;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Product product;
	
	
	
}
