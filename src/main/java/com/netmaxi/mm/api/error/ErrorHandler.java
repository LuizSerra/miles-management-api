package com.netmaxi.mm.api.error;

import org.springframework.http.HttpStatus;
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
	public ResponseEntity handleErro404(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleErro400(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(DadosErrosValidacao::new));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity handleIlleGalArgument(IllegalArgumentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity handleValidation(ValidationException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(TransactionBusinessException.class)
	public ResponseEntity handleTransactionBusinsess(TransactionBusinessException ex) {
		return ResponseEntity.internalServerError().body(ex.getMessage());
	}
	
	private record DadosErrosValidacao(String campo, String Mensagem) {
		public DadosErrosValidacao(FieldError field) {
			this(field.getField(), field.getDefaultMessage());
		}
	}
	
}
