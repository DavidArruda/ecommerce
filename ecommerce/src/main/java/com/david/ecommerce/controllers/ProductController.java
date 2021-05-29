package com.david.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.ecommerce.model.Product;
import com.david.ecommerce.services.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	private final ProductService service;

	public ProductController(@Autowired ProductService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
		return new ResponseEntity<>(service.save(product), HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
		return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> findById(@PathVariable long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Product> update (@PathVariable long id, @RequestBody Product product) {
		return new ResponseEntity<>(service.update(product), HttpStatus.OK);
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Product> delete(@PathVariable long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
