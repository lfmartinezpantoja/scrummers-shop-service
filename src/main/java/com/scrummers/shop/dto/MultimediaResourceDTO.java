package com.scrummers.shop.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MultimediaResourceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String url;
}
