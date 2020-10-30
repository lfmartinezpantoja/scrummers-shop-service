package com.scrummers.shop.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.scrummers.shop.dto.MultimediaResourceDTO;
import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ResponseDTO;

public interface ProductService {

	public ResponseDTO<ProductDTO> create(ProductDTO productDTO);

	public ResponseDTO<ProductDTO> get(Long productId);

	public ResponseDTO<ProductDTO> update(ProductDTO productDTO);

	public ResponseDTO<ProductDTO> disable(Long productId);

	public ResponseDTO<ProductDTO> addMultimediaResource(MultipartFile productFile, Long productId) throws IOException;

	public ResponseDTO<MultimediaResourceDTO> getMultimediaResource(String productFileName, Long productId)
			throws IOException;

}
