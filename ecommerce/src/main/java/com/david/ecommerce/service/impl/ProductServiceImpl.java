package com.david.ecommerce.service.impl;

import com.david.ecommerce.dto.ProductDTO;
import com.david.ecommerce.exception.EntityNotFoundException;
import com.david.ecommerce.exception.ProblemType;
import com.david.ecommerce.model.Image;
import com.david.ecommerce.model.Product;
import com.david.ecommerce.repository.AttributeRepository;
import com.david.ecommerce.repository.ImageRepository;
import com.david.ecommerce.repository.ProductRepository;
import com.david.ecommerce.service.CategoryService;
import com.david.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;
	private final CategoryService categoryService;
	private final ImageRepository repositoryImg;
	private final AttributeRepository repositoryAttribute;

	public ProductServiceImpl(ProductRepository repository, CategoryService categoryService,
							  ImageRepository repositoryImg, AttributeRepository attributeRepository) {
		this.repository = repository;
		this.categoryService = categoryService;
		this.repositoryImg = repositoryImg;
		this.repositoryAttribute = attributeRepository;
	}

	@Override
	public Product save(ProductDTO product) {
		categoryService.exists(product.getCategories());

		existsAttributes(product.getAttributes());

		Product prdt = new Product(null,
								   product.getName(),
				                   product.getSku(),
				                   product.getStatus(),
				                   product.getEan(),
				                   product.getBrand(),
				                   product.getDescription(),
								   product.getQty(),
				                   product.getCostPrice(),
				                   product.getPrice(),
				                   null,
				                   null,
				                   null);

		/*var prdt = Product.builder()
									.name(product.getName())
									.sku(product.getSku())
									.status(product.getStatus())
									.description(product.getDescription())
									.brand(product.getBrand())
									.qty(product.getQty())
									.costPrice(product.getCostPrice())
									.price(product.getPrice())
									.build();
		*/
		//prdt = repository.save(prdt);
		
		//saveImages(product.getImages(), prdt);

		return null;
	}

	private void saveImages(Set<String> imgs, Product product) {
		if (imgs != null && !imgs.isEmpty()) {
			for (String img : imgs) {
				repositoryImg.save(new Image(null, img, product));
			}
		}
	}

	private void existsAttributes(Set<String> attributes) {
		for (String attr : attributes) {
			if (!repositoryAttribute.existsById(attr))
				throw new EntityNotFoundException(ProblemType.ATTRIBUTE_NOT_FOUND);
		}
	}

	@Override
	public Page<ProductDTO> find(Pageable pageRequest) {
		Page<Product> page = repository.findAll(pageRequest);
		repository.findProduct(page.stream().collect(Collectors.toList()));
		return page.map(x -> new ProductDTO());
	}

	@Override
	public Product findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND));
	}

	@Override
	public Product update(Product product) {
		if (!repository.existsById(product.getId()))
			throw new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND);

		return repository.save(product);
	}

	@Override
	public void delete(Long id) {
		if (!repository.existsById(id))
			throw new EntityNotFoundException(ProblemType.PRODUCT_NOT_FOUND);

		repository.deleteById(id);
	}

}
