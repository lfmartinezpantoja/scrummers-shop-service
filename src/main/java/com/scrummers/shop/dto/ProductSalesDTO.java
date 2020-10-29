package com.scrummers.shop.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSalesDTO implements Serializable {

	@ApiModelProperty(notes = "product identifier",example = "1")
	private Long productId;
	@ApiModelProperty(notes = "product shop identifier",example = "1")
	private Long shopId;
	@ApiModelProperty(notes = "product quantity",example = "1")
	private int quantity;
	private static final long serialVersionUID = 5551056559008091998L;

}
