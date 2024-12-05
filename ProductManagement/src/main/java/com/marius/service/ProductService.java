package com.marius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marius.model.Product;
import com.marius.model.User;
import com.marius.repository.ProductRepository;
import com.marius.repository.UserRepository;

//In this class are implemented the methods
@Service	//Allow to use the methods defined in this class on the controller class
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> retrieveAllProducts() {
		return productRepository.findAll();
	}

	public Product retrieveProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return product.get();
	}

	//This method will delete a product. It is calling productRepository.deleteById(id).
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	//This method will create a new product and save it. It use @RequestBody to map the user details from request to bean.
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
}
