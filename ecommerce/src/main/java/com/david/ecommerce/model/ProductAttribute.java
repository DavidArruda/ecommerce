package com.david.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products_attributes")
public class ProductAttribute {

	@Id
	private String key;

	@Column(length = 120, nullable = false)
	private String value;

	public ProductAttribute() {
		// CONSTRUTOR VAZIO
	}

	public ProductAttribute(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key.toUpperCase();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
