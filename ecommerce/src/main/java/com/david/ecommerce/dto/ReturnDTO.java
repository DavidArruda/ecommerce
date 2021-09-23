package com.david.ecommerce.dto;

public class ReturnDTO {
	
	private String code;
	private String message;
	private String id;
	
	public ReturnDTO(String code, String message, String id) {
		super();
		this.code = code;
		this.message = message;
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
