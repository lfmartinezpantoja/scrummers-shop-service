package com.scrummers.shop.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

	private final int code;
	private final String description;
	private final HttpStatus httpStatus;
	private static final long serialVersionUID = 1L;

}
