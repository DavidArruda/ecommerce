package com.david.ecommerce.exception;

public enum ProblemType {
	
	ENTITY_NOT_FOUND("Entidade não encontrada", "Entidade não encotrada na base de dados, informe um entidade válida"),
	PRODUCT_NOT_FOUND("Produto não encontrado", "Produto não encotrada na base de dados, informe um produto válido"),
	CATEGORY_NOT_FOUND("Categoria não encontrada", "Categoria não encotrada na base de dados, informe uma categoria válida"),
	IMAGE_NOT_FOUND("Imagem não encontrada", "Imagem não encotrada na base de dados, informe uma imagem válida");
	
	private final String description;
	private final String detail;
	
	private ProblemType(String description, String detail) {
		this.description = description;
		this.detail = detail;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDetail() {
		return detail;
	}

}
