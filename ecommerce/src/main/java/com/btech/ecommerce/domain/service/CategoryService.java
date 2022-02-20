package com.btech.ecommerce.domain.service;

import com.btech.ecommerce.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CategoryService {

    /**
     * Salva uma nova categoria na base de dados.
     * @param category
     * @return Category com o id gerado no bando de dados.
     */
    Category save(Category category);

    Page<Category> findAll(Pageable pageable);

    Category findById(Category id);

    Category update(Category category);

    void delete(Category id);

    void exists(Category id);

    void exists(Set<Category> categories);

    Set<Category> findByIds(Set<Category> ids);
}
