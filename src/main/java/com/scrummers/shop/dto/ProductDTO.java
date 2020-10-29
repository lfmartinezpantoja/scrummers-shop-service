package com.scrummers.shop.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO implements Serializable {

	@ApiModelProperty(notes = "Product identifier", example = "1")
	private Long id;
	@ApiModelProperty(notes = "Product name", example = "Dress")
	@NotNull(message = "Product name can't be null")
	private String name;
	@ApiModelProperty(notes = "Product size", example = "XL")
	@NotNull(message = "Product size can't be null")
	private String size;
	@ApiModelProperty(notes = "Quantity product", example = "1")
	@Min(value = 1, message = "Quantity must be at least 1")
	private int quantity;
	@ApiModelProperty(notes = "Product price", example = "1")
	@Min(value = 1, message = "Unit price must be at least 1 dollar")
	private double unitPrice;
	@ApiModelProperty(notes = "Product creaction date", example = "2020-10-29T03:29:20.745Z")
	@NotNull(message = "Product creation date  can't be null")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Bogota")
	private Date creationDate;
	@ApiModelProperty(notes = "Product status", example = "false")
	private Boolean disable;
	@ApiModelProperty(notes = "Shop id", example = "1")
	private Long shopId;
	private static final long serialVersionUID = 1L;

}
