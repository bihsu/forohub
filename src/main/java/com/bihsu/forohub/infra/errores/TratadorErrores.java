package com.bihsu.forohub.infra.errores;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bihsu.forohub.domain.ValidacionException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorErrores {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> notValuePresent(){
		return ResponseEntity.badRequest().body("El elemento no existe");
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> tratarError404(){
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<DatosErrorValidacion>> tratarError400(MethodArgumentNotValidException e){
		var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
		return ResponseEntity.badRequest().body(errores);
	}
	
	@ExceptionHandler(ValidacionException.class)
	public ResponseEntity<String> tratarErrorDeValidacion(ValidacionException e) {
		return ResponseEntity.badRequest().body(e.getMessage());	
	}
	
	private record DatosErrorValidacion(String campo, String error) {
		public DatosErrorValidacion(FieldError error) {
			// TODO Auto-generated constructor stub
			this(error.getField(), error.getDefaultMessage());
		}
	}

}
