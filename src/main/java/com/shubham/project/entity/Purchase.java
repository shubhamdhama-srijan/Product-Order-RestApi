package com.shubham.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Purchase_table")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Purchase {

	@Id
	@GeneratedValue
	private int purchId;
	
	@Column(name = "purchaseDate")
	private Date purchDate;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Product product;
	
	
	
}