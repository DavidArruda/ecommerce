package com.btech.ecommerce.domain.exception;

public class EntityNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public EntityNotFoundException(ProblemType mensagem) {
		super(mensagem.getDetail());
	}
}
