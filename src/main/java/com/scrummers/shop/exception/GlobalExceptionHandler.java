package com.scrummers.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.scrummers.shop.dto.ErrorDTO;
import com.scrummers.shop.exception.error.Error;

import lombok.extern.java.Log;

@Log
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDTO> exception(Exception exception) {
		log.info("Ocurrio un error no controlado, error: " + exception.getMessage());
		return new ResponseEntity<>(
				new ErrorDTO(Error.DEFAULT_EXCEPTION.getCode(), Error.DEFAULT_EXCEPTION.getDescription()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseBody
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ErrorDTO> exception(CustomException exception) {
		log.info(String.format("Ocurrio un error  controlado, codigo error: %s, descripcion error: %s, status Http: %s",
				exception.getCode(), exception.getDescription(), exception.getHttpStatus().value()));
		return new ResponseEntity<>(new ErrorDTO(exception.getCode(), exception.getDescription()),
				exception.getHttpStatus());
	}

	@ResponseBody
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<ErrorDTO> exception(NotFoundException exception) {
		log.info(String.format(
				"Ocurrio un error controlado, Recurso no encontrado: nombre: %s, identificacion recurso no encontrado: %s, status Http: 404",
				exception.getIdResource(), exception.getIdResource()));
		return new ResponseEntity<>(new ErrorDTO(Error.RESOURCE_NOT_FOUND.getCode(), String.format(
				Error.RESOURCE_NOT_FOUND.getDescription(), exception.getResourceName(), exception.getIdResource())),
				HttpStatus.NOT_FOUND);
	}

}
