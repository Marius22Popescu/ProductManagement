package com.marius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marius.model.Product;

//ProductRepository extends the JpaRepository in order to allow the ProductServicece class to use just the desired methods from the
//JpaRepository and define custom methods.

@Repository	//Allow to do CRUD operation on the entity class
public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findByPriceLessThan(Long price);

}
