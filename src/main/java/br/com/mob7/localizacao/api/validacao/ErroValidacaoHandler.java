package br.com.mob7.localizacao.api.validacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErroValidacaoHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<>();
		Map<String, List<String>> result = new HashMap<>();
		
		errors.add("Data inicial ou data final inválidas!");
		errors.add(ex.getMessage());
		result.put("errors", errors);
		
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<>();

		ex.getConstraintViolations().forEach(cv -> errors.add(cv.getMessage()));

		Map<String, List<String>> result = new HashMap<>();
		result.put("errors", errors);

		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

}
