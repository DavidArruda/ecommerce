package com.david.ecommerce.service;

import com.david.ecommerce.model.Category;
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

    Category findById(long id);

    Category update(Category category);

    void delete(Long id);

    void exists(Long id);

    void exists(Set<Long> categories);

    Set<Category> findByIds(Set<Long> ids);
}
