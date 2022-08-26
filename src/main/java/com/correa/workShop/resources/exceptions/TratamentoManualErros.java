package com.correa.workShop.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.correa.workShop.services.exceptions.TratamentoErroDB;
import com.correa.workShop.services.exceptions.RecursoNãoEncontradoException;

@ControllerAdvice
public class TratamentoManualErros {
	

	//tratamento do erro com exceção personalizada 
	//anotação capaz de interceptar exceção
	@ExceptionHandler(RecursoNãoEncontradoException.class)
	public ResponseEntity<ErroPadrao> recursoNaoEncontrado(RecursoNãoEncontradoException e, HttpServletRequest request) {
		String error = "Recurso não encontrado!";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao err = new ErroPadrao(Instant.now(),
				status.value(), error,
				e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(TratamentoErroDB.class)
	public ResponseEntity<ErroPadrao> database(TratamentoErroDB e, HttpServletRequest request) {
		String error = "Erro no Banco de Dados!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroPadrao err = new ErroPadrao(Instant.now(), 
				status.value(), 
				error, e.getMessage(), 
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
