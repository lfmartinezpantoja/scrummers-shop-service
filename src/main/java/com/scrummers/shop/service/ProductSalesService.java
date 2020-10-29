package com.scrummers.shop.service;

import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ProductSalesDTO;
import com.scrummers.shop.dto.ResponseDTO;

public interface ProductSalesService {

	public ResponseDTO<ProductDTO> registSale(ProductSalesDTO productSalesDTO);
}
