package com.btech.ecommerce.api.v1.controller;

import com.btech.ecommerce.domain.model.Category;
import com.btech.ecommerce.domain.model.dto.ReturnDTO;
import com.btech.ecommerce.domain.service.impl.CategoryServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

	private final CategoryServiceImpl service;

	public CategoryController(CategoryServiceImpl service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody  @Valid Category category) {
		service.save(category);
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

//	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
//	public ResponseEntity<Category> findById(@PathVariable long id) {
//		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
//	}

}
