package com.btech.ecommerce.domain.service.impl;

import com.btech.ecommerce.domain.model.Category;
import com.btech.ecommerce.domain.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btech.ecommerce.domain.exception.EntityNotFoundException;
import com.btech.ecommerce.domain.exception.ProblemType;
import com.btech.ecommerce.domain.repository.CategoryRepository;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;

	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}

	@Override
	public Category save(Category category) {
		return repository.save(category);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Category findById(Category id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND));
	}

	@Override
	public Category update(Category category) {
		if (!repository.existsById(category))
			throw new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND);

		return repository.save(category);
	}

	@Override
	public void delete(Category id) {
		exists(id);
		repository.deleteById(id);
	}

	@Override
	public void exists(Category id) {
		if (!repository.existsById(id))
			throw new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND);
	}

	@Override
	public void exists(Set<Category> categories) {
		categories.forEach(c -> exists(c));
	}

	@Override
	public Set<Category> findByIds(Set<Category> ids) {
		return (Set<Category>) repository.findAllById(ids);
	}

}
