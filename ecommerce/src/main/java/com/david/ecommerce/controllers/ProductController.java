package com.david.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.ecommerce.model.Product;
import com.david.ecommerce.repositories.ProductRepository;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
	
	public ProductController(@Autowired ProductRepository repository) {
		this.repository = repository;
	}
	private final ProductRepository repository;
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		if (product == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(repository.save(product), HttpStatus.CREATED);
	}

}
