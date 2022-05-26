package com.shubham.project.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@Column(name = "purchaseId")
	private int purchId;
	
	@Column(name = "purchaseDate")
	private Date purchDate;
	
//	@ManyToOne
//	private Product product;
	
	
	
}
