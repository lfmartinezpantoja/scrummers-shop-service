package com.scrummers.shop.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "Shop identifier", example = "1")
	private Long id;
	@ApiModelProperty(notes = "Shop name", example = "Shop #1")
	@NotNull(message = "Shop name can't be null")
	private String name;
	@ApiModelProperty(notes = "Shop address", example = "Cra 10 #121-1")
	@NotNull(message = "Shop address can't be null")
	private String address;
	@ApiModelProperty(notes = "Shop status", example = "false")
	private Boolean disable;
	private List<ProductDTO> productsDTO;
}
