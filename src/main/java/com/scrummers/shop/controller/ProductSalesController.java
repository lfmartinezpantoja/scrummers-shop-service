package com.scrummers.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ProductSalesDTO;
import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.service.ProductSalesService;

@RestController
public class ProductSalesController {

	@Autowired
	ProductSalesService productSalesService;

	@PostMapping("/sales")
	public ResponseEntity<ResponseDTO<ProductDTO>> registSale(@RequestBody ProductSalesDTO productSalesDTO) {
		return new ResponseEntity<>(productSalesService.registSale(productSalesDTO), HttpStatus.OK);
	}

}
