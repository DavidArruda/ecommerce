package com.david.ecommerce.service.impl;

import com.david.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.david.ecommerce.exception.EntityNotFoundException;
import com.david.ecommerce.exception.ProblemType;
import com.david.ecommerce.model.Category;
import com.david.ecommerce.repository.CategorieRepository;

import java.util.Iterator;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategorieRepository repository;

	public CategoryServiceImpl(CategorieRepository repository) {
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
	public Category findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND));
	}

	@Override
	public Category update(Category category) {
		if (!repository.existsById(category.getId()))
			throw new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND);

		return repository.save(category);
	}

	@Override
	public void delete(Long id) {
		exists(id);
		repository.deleteById(id);
	}

	@Override
	public void exists(Long id) {
		if (!repository.existsById(id))
			throw new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND);
	}

	@Override
	public void exists(Set<Long> categories) {
		for(Long id: categories)
			exists(id);
	}

	@Override
	public Set<Category> findByIds(Set<Long> ids) {
		return (Set<Category>) repository.findAllById(ids);
	}

}
