package com.netmaxi.mm.api.error;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class TratamentoDeErros {
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(DadosErrosValidacao::new));
	}
	
	private record DadosErrosValidacao(String campo, String Mensagem) {
		public DadosErrosValidacao(FieldError field) {
			this(field.getField(), field.getDefaultMessage());
		}
	}
	
}
