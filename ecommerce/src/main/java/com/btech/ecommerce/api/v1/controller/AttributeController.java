package com.btech.ecommerce.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.btech.ecommerce.domain.model.dto.ReturnDTO;
import com.btech.ecommerce.domain.exception.EntityNotFoundException;
import com.btech.ecommerce.domain.exception.ProblemType;
import com.btech.ecommerce.domain.model.ProductAttribute;
import com.btech.ecommerce.domain.repository.AttributeRepository;

@RestController
@RequestMapping(path = "/attributes")
public class AttributeController {

	private final AttributeRepository repository;

	public AttributeController(@Autowired AttributeRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<ReturnDTO> create(@RequestBody @Valid ProductAttribute productAttribute) {
		repository.save(productAttribute);
		return new ResponseEntity<>(new ReturnDTO(HttpStatus.CREATED, productAttribute.getKey()), HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ProductAttribute>> findAll() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{key}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProductAttribute> findById(@PathVariable String key) {
		return new ResponseEntity<>(
				repository.findById(key.toUpperCase()).orElseThrow(() -> new EntityNotFoundException(ProblemType.ATTRIBUTE_NOT_FOUND)),
				HttpStatus.OK);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<ProductAttribute> update(@PathVariable String id, @RequestBody ProductAttribute productAttribute) {
		return new ResponseEntity<>(repository.save(productAttribute), HttpStatus.OK);
	}

	@DeleteMapping(path = { "/{key}" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String key) {
		repository.deleteById(key.toUpperCase());
	}

}
