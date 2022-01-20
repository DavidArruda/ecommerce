package com.david.ecommerce.service;

import com.david.ecommerce.dto.ProductDTO;
import com.david.ecommerce.model.Product;
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
