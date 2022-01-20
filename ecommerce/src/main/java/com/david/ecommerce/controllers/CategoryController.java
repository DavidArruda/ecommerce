package com.david.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.ecommerce.dto.ReturnDTO;
import com.david.ecommerce.model.Category;
import com.david.ecommerce.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

	private final CategoryServiceImpl service;

	public CategoryController(@Autowired CategoryServiceImpl service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ReturnDTO> create(@RequestBody  @Valid Category category) {
		service.save(category);
		var returnDTO = new ReturnDTO("201", "Created", category.getId().toString()); 
		return new ResponseEntity<>(returnDTO, HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
		return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Category> findById(@PathVariable long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}

}
