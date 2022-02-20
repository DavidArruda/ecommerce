package com.btech.ecommerce.domain.model.dto;

import org.springframework.http.HttpStatus;

public record ReturnDTO(int code, String message, String id) {

	public ReturnDTO(HttpStatus status, String id) {
		this(status.value(), status.name(), id);
	}

}
