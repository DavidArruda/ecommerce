package com.david.ecommerce.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products_attributes")
public class ProductAttribute {

	@Id
	private String key;

	@Column(length = 120, nullable = false)
	private String value;

	@JsonIgnore
	@ManyToMany(mappedBy = "attributes", fetch = FetchType.EAGER)
	private Set<Product> products;

	public ProductAttribute() {
		// CONSTRUTOR VAZIO
	}

	public ProductAttribute(String key, String value, Set<Product> products) {
		super();
		this.key = key;
		this.value = value;
		this.products = products;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
