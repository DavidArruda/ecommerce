package com.btech.ecommerce.domain.model.dto;

import com.btech.ecommerce.domain.model.Category;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

public class ProductDTO {

	public ProductDTO() {}
	
	public ProductDTO(String name, String sku, Boolean status, String ean, String brand, String description,
			Integer qty, BigDecimal costPrice, BigDecimal price, Set<Category> categories, Set<String> images,
			@Min(1) Set<String> attributes) {
		super();
		this.name = name;
		this.sku = sku;
		this.status = status;
		this.ean = ean;
		this.brand = brand;
		this.description = description;
		this.qty = qty;
		this.costPrice = costPrice;
		this.price = price;
		this.categories = categories;
		this.images = images;
		this.attributes = attributes;
	}

	@NotBlank(message = "Campo name não pode ser nulo ou vazio")
	@Size(min = 3, max = 120, message = "Campo nome deve conter entre 3 e 120 caracteres")
	private String name;

	@NotBlank(message = "Campo sku não pode ser nulo ou vazio")
	private String sku;

	private Boolean status;

	private String ean;

	@Size(min = 1, max = 90, message = "Campo brand deve conter entre 1 e 90 caracteres")
	private String brand;

	@NotBlank(message = "Campo description não pode ser nulo ou vazio")
	@Size(min = 3, message = "Campo description deve conter no mínimo 3 caracteres")
	private String description;

	@Min(value = 0, message = "Campo qty deve ser >= 0")
	private Integer qty;

	@DecimalMin(value = "0.0", message = "Campo costPrice deve ser maior >= 0")
	@Digits(integer = 15, fraction = 4, message = " Campo costPrice fora do limite. Esperado: numeric 15,4")
	private BigDecimal costPrice;

	@DecimalMin(value = "0.0", message = "Campo price deve ser maior >= 0")
	@Digits(integer = 15, fraction = 4, message = " Campo price fora do limite. Esperado: numeric 15,4")
	private BigDecimal price;

	@Min(value = 1)
	private Set<Category> categories;

	private Set<String> images;

	@Min(value = 1)
	Set<String> attributes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public Set<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<String> attributes) {
		this.attributes = attributes;
	}

}
