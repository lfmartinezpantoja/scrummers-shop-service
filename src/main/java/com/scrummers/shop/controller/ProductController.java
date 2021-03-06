package com.scrummers.shop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scrummers.shop.dto.MultimediaResourceDTO;
import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/products")
	@ApiOperation(value = "Create  product", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED") })
	public ResponseEntity<ResponseDTO<ProductDTO>> create(@RequestBody ProductDTO productDTO) {
		return new ResponseEntity<>(productService.create(productDTO), HttpStatus.CREATED);
	}

	@GetMapping("/products/{id}")
	@ApiOperation(value = "Get  product", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public ResponseEntity<ResponseDTO<ProductDTO>> get(@PathVariable Long id) {
		return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
	}

	@PatchMapping("/products")
	@ApiOperation(value = "Update  product", httpMethod = "PATCH")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public ResponseEntity<ResponseDTO<ProductDTO>> update(@RequestBody ProductDTO productDTO) {
		return new ResponseEntity<>(productService.update(productDTO), HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	@ApiOperation(value = "Disable  product", httpMethod = "DELETE")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public ResponseEntity<ResponseDTO<ProductDTO>> disable(@PathVariable Long id) {
		return new ResponseEntity<>(productService.disable(id), HttpStatus.OK);
	}

	@PostMapping("/products/{id}/multimedia-resource")
	@ApiOperation(value = "Add multimedia resource to product", httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public ResponseEntity<ResponseDTO<ProductDTO>> addMultimediaResource(
			@RequestParam("multimedia-resource") MultipartFile productFile, @PathVariable Long id) throws IOException {
		return new ResponseEntity<>(productService.addMultimediaResource(productFile, id), HttpStatus.OK);
	}

	@GetMapping("/products/{id}/multimedia-resource/{productFileName:.+}")
	@ApiOperation(value = "Get multimedia resource to product", httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public ResponseEntity<ResponseDTO<MultimediaResourceDTO>> getMultimediaResource(@PathVariable Long id,
			@PathVariable String productFileName) throws IOException {
		return new ResponseEntity<>(productService.getMultimediaResource(productFileName, id), HttpStatus.OK);
	}



}
