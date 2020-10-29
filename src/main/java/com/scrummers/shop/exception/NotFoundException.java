package com.scrummers.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

	private final String resourceName;
	private final String idResource;
	private static final long serialVersionUID = 1L;

}
