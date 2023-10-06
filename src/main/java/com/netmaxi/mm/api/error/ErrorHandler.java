package com.netmaxi.mm.api.error;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class ErrorHandler {
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity handleErro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleErro400(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(DadosErrosValidacao::new));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity handleIlleGalArgument(IllegalArgumentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(EntityAlreadyCreatedException.class)
	public ResponseEntity handleEntityAlreadyCreated(EntityAlreadyCreatedException ex) {
		return ResponseEntity.internalServerError().body(ex.getMessage());
	}
	
	private record DadosErrosValidacao(String campo, String Mensagem) {
		public DadosErrosValidacao(FieldError field) {
			this(field.getField(), field.getDefaultMessage());
		}
	}
	
}
