package com.scrummers.shop.service;

import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ResponseDTO;

public interface ProductService {

	public ResponseDTO<ProductDTO> create(ProductDTO productDTO);

	public ResponseDTO<ProductDTO> get(Long productId);

	public ResponseDTO<ProductDTO> update(ProductDTO productDTO);

	public ResponseDTO<ProductDTO> disable(Long productId);
}
