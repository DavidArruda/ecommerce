package com.btech.ecommerce.domain.service;

import com.btech.ecommerce.domain.model.dto.ProductDTO;
import com.btech.ecommerce.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product save(ProductDTO product);

    Page<ProductDTO> find(Pageable pageRequest);

    Product findById(long id);

    Product update(Product product);

    void delete(Long id);
}
