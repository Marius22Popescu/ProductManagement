package com.marius.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

//In this class is defined the product entity
@Entity	//Maps the object to the spring container
@Table(name = "product")	//Map the object to the DB
public class Product {

	@Id	//It make the field id primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	@NotNull
	private Long price;

	@ManyToOne //@ManyToOne annotation is associated with User class variable. @JoinColumn annotation references the mapped column.
	@JoinColumn(name = "user_id", nullable = false)  //Joins the user_id to the column to the "Product" table and the user_id can not be null, it is the id from User class
	@JsonIgnore //Is not printing to the Json
	private User owner;


	public Long getId() {
		return productId;
	}

	public void setId(Long id) {
		this.productId = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
