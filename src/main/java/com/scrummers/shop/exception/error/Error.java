package com.scrummers.shop.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Error {

	DEFAULT_EXCEPTION(1, "An error has occurred, try again later or contact your administrator"),
	RESOURCE_NOT_FOUND(2, "Resource %s identified with %s not found"),
	RESOURCE_ALREADY_EXIST(3, "Resource %s identified with %s already exist"),
	RESOURSE_IS_DISABLE(4, "Resource %s identified with %s is disable, please enable it and try again"),
	PRODUCT_NOT_AVAILABLE_IN_SHOP(5, "Product identified with %s not available in shop %s"),
	QUANTITY_NOT_AVAILABLE(6, "Quantity not available in shop %s");

	private int code;
	private String description;
}
