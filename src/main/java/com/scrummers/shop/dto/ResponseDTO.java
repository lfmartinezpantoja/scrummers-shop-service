package com.scrummers.shop.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean success;
	private transient T data;

	public ResponseDTO(boolean success) {
		super();
		this.success = success;
	}

}
