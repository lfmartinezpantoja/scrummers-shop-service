package com.scrummers.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.dto.ShopDTO;
import com.scrummers.shop.service.ShopService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ShopController {

	@Autowired
	ShopService shopService;

	@PostMapping("/shops")
	@ApiOperation(value = "Create  shop", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	public ResponseEntity<ResponseDTO<ShopDTO>> create(@RequestBody ShopDTO shopDTO) {
		return new ResponseEntity<>(shopService.create(shopDTO), HttpStatus.CREATED);
	}

	@GetMapping("/shops/{id}")
	@ApiOperation(value = "Get  shop", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "OK") })
	public ResponseEntity<ResponseDTO<ShopDTO>> get(@PathVariable Long id) {
		return new ResponseEntity<>(shopService.get(id), HttpStatus.OK);
	}

	@PatchMapping("/shops")
	@ApiOperation(value = "Update  shop", httpMethod = "PATCH")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public ResponseEntity<ResponseDTO<ShopDTO>> update(@RequestBody ShopDTO shopDTO) {
		return new ResponseEntity<>(shopService.update(shopDTO), HttpStatus.OK);
	}

	@DeleteMapping("/shops/{id}")
	@ApiOperation(value = "Disable  shop", httpMethod = "DELETE")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public ResponseEntity<ResponseDTO<ShopDTO>> disable(@PathVariable Long id) {
		return new ResponseEntity<>(shopService.disable(id), HttpStatus.OK);
	}
}
