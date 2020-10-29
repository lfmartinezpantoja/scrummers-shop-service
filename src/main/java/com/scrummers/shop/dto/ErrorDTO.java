package com.scrummers.shop.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int code;
	private String description;

}
