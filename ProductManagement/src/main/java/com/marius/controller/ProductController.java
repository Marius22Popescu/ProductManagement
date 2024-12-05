package com.marius.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marius.model.Product;
import com.marius.model.User;
import com.marius.repository.ProductRepository;
import com.marius.repository.UserRepository;
import com.marius.service.ProductService;
import com.marius.service.UserService;

//This class provide RESTful end points for the the UI user
@RestController //Spring come here to check for the RESTful end points
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public ResponseEntity<List<Product>> retrieveAll(){
		return ResponseEntity.ok(productService.retrieveAllProducts());
	}

	@GetMapping("/product/{id}")
	public ResponseEntity getProductById (@RequestBody Product product, @PathVariable Long id) {
		return ResponseEntity.ok(productService.retriveProduct(id));
	}

    // Get products with a given price.
    @GetMapping("/product/priceEqual/{price}")
    public ResponseEntity<List<Product>> getProductWithPrice(@PathVariable Long price) {
        List<Product> product = productService.retrieveAllProducts().stream()
                .filter(prod -> prod.getPrice().equals(price))
                .collect(Collectors.toList());
        return ResponseEntity.ok(product);
    }

    //Get products less than a price
    @GetMapping("/products/price/{price}")
    public List<Product> getProductLessThanPrice(@PathVariable Long price) {
        List<Product> result = productRepository.findByPriceLessThan(price);
        return result;
    }


	@DeleteMapping("/product/{id}")
	public void deleteProductById (@PathVariable Long id) {
		productService.deleteProduct(id);
	}

	  // Create a new product
    @PostMapping("/product/{id}")
    public Product createProduct(@RequestBody @Valid Product product, @PathVariable Long id) {
        User user = this.userService.retrieveUser(id).get();
        product.setOwner(user);
        productService.createProduct(product);
        return new Product();
    }

	//This method is using PUT method in order to update a product.
	@PutMapping("/product/edit/{id}") //is mapping a put request at the specified uri
	public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
		Product p = this.productService.retrieveProduct(id); //get the product by id
		User u = p.getOwner(); //get the owner of product
		product.setId(id);	//set id for the product
		product.setOwner(u);	//set owner for the product
		productService.createProduct(product);
		return new Product();
	}
}
