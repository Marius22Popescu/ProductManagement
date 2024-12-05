package com.marius.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


//In this class is defined the user entity object
@Entity //Maps the object to the spring container
@Table(name = "user") //Map the object to the DB
public class User {

	@Id //It make the field id primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //It is generating a value
	private Long id;

	@Column(name = "username")
	@NotNull
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "type")
	private Long type;

	@OneToMany(mappedBy="owner", cascade = CascadeType.REMOVE, orphanRemoval = true) // @OneToMany annotation is used to define the field from "Product" class
	//that will be used to map the mappedBy variable. The field owner from product will map the user from user object
	private Set<Product> product = new HashSet<>();

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
}
