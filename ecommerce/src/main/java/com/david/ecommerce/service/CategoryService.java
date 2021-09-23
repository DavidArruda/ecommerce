package com.david.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.david.ecommerce.exception.EntityNotFoundException;
import com.david.ecommerce.exception.ProblemType;
import com.david.ecommerce.model.Category;
import com.david.ecommerce.repository.CategorieRepository;

@Service
public class CategoryService {

	private final CategorieRepository repository;

	public CategoryService(@Autowired CategorieRepository repository) {
		this.repository = repository;
	}

	public Category save(Category category) {
		return repository.save(category);
	}

	public Page<Category> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Category findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND));
	}

	public Category update(Category category) {
		if (!repository.existsById(category.getId())) {
			throw new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND);
		}

		return repository.save(category);
	}

	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND);
		}
		repository.deleteById(id);
	}

}
