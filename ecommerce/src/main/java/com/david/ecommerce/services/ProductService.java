package com.david.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.david.ecommerce.exception.EntityNotFoundException;
import com.david.ecommerce.exception.ProblemType;
import com.david.ecommerce.model.Image;
import com.david.ecommerce.model.Product;
import com.david.ecommerce.repositories.CategorieRepository;
import com.david.ecommerce.repositories.ImageRepository;
import com.david.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;
	private final CategorieRepository repositoryCateg;
	private final ImageRepository repositoryImg;

	public ProductService(@Autowired ProductRepository repository, @Autowired CategorieRepository repositoryCateg, ImageRepository repositoryImg) {
		this.repository = repository;
		this.repositoryCateg = repositoryCateg;
		this.repositoryImg = repositoryImg;
	}

	public Product save(Product product) {
		if (!repositoryCateg.existsById(product.getCategory().getId())) {
			throw new EntityNotFoundException(ProblemType.CATEGORY_NOT_FOUND.getDetail());
		}
		
		repository.save(product);
		
		if (product.getImages() != null && !product.getImages().isEmpty()) {
			for (Image img : product.getImages()) {
				img.setProduct(product);
				repositoryImg.save(img);
			}	
		}
		
		return product;
	}

	public Page<Product> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Product findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND.getDetail()));
	}

	public Product update(Product product) {
		if (!repository.existsById(product.getId())) {
			throw new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND.getDetail());
		}

		return repository.save(product);
	}

	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND.getDetail());
		}
		repository.deleteById(id);
	}

}
