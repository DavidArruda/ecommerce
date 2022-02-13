package com.btech.ecommerce.domain.exception;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe para captar os erros gerados no back-end e fornecer as mesagens de
 * erro para o front-end.
 * 
 * @author David Arruda
 *
 */
@RestControllerAdvice
@ControllerAdvice
public class ControlExcep extends ResponseEntityExceptionHandler {

	/* Tratamento da maioria dos erros */
	@Override
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		var msg2 = new StringBuilder();

		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError objectError : list) {
				msg2.append("[");
				msg2.append(objectError.getDefaultMessage());
				msg2.append("] ");
			}
		} else {
			msg2.append(ex.getMessage());
		}

		var objetoError = new ObjErrorApi();
		objetoError.setError(msg2.toString());
		objetoError.setCode(status.value() + " ==> " + status.getReasonPhrase());

		return new ResponseEntity<>(objetoError, headers, status);
	}

	/* Tratamento da maioria dos erros a nível do banco de dados */
	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		String msg;

		if (ex instanceof DataIntegrityViolationException) {
			msg = ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();

		} else if (ex instanceof ConstraintViolationException) {
			msg = ((ConstraintViolationException) ex).getCause().getCause().getMessage();

			// } else if (ex instanceof PSQLException) {
			// msg = ((PSQLException) ex).getCause().getCause().getMessage();

		} else if (ex instanceof SQLException) {
			msg = ((SQLException) ex).getCause().getCause().getMessage();

		} else {
			msg = ex.getMessage();
		}

		var objetoError = new ObjErrorApi();
		objetoError.setError(msg);
		objetoError.setCode(
				HttpStatus.INTERNAL_SERVER_ERROR + " ==> " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

		return new ResponseEntity<>(objetoError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontrada(EntityNotFoundException ex, WebRequest request) {

		var status = HttpStatus.NOT_FOUND;
		var objetoError = new ObjErrorApi();
		objetoError.setError(ex.getMessage());
		objetoError.setCode(HttpStatus.NOT_FOUND.toString());

		return handleExceptionInternal(ex, objetoError, new HttpHeaders(), status, request);
	}

}