package com.david.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.ecommerce.model.Category;
import com.david.ecommerce.repositories.CategorieRepository;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

	private final CategorieRepository repository;

	public CategoryController(@Autowired CategorieRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category) {
		return new ResponseEntity<>(repository.save(category), HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Category>> findAll() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Category> findById(@PathVariable long id) {
		return repository.findById(id).map(category -> ResponseEntity.ok().body(category))
				.orElse(ResponseEntity.notFound().build());
	}

}
