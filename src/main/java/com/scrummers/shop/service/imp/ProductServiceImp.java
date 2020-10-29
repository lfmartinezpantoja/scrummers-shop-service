package com.scrummers.shop.service.imp;

import static com.scrummers.shop.helpers.Constants.SUCCESFULLY_REQUEST_LOG;

import java.util.Optional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.exception.CustomException;
import com.scrummers.shop.exception.NotFoundException;
import com.scrummers.shop.exception.error.Error;
import com.scrummers.shop.model.Product;
import com.scrummers.shop.repository.ProductRepository;
import com.scrummers.shop.service.ProductService;

import lombok.extern.java.Log;

@Log
@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	Gson gson;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseDTO<ProductDTO> create(ProductDTO productDTO) {
		log.info(String.format("--- Se ha recibido una peticion para creacion de producto, json recibido: %s ----",
				gson.toJson(productDTO)));
		Product product = new Product();
		modelMapper.map(productDTO, product);
		productRepository.save(product);
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true);
	}

	@Override
	public ResponseDTO<ProductDTO> get(Long productId) {
		log.info(String.format("--- Se ha recibido una peticion para consulta de producto, id recibido: %s ----",
				productId));
		ProductDTO productDTO = new ProductDTO();
		Product product = isProductPresent(productId);
		if (product.isDisable()) {
			throw new CustomException(Error.RESOURSE_IS_DISABLE.getCode(),
					String.format(Error.RESOURSE_IS_DISABLE.getDescription(), Product.class.getSimpleName(),
							Long.toString(productId)),
					HttpStatus.BAD_REQUEST);
		}
		modelMapper.map(product, productDTO);
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true, productDTO);
	}

	@Override
	public ResponseDTO<ProductDTO> update(ProductDTO productDTO) {
		log.info(
				String.format("--- Se ha recibido una peticion para actualizacion de producto, json recibido: %s ----",
						gson.toJson(productDTO)));
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		Product product = isProductPresent(productDTO.getId());
		modelMapper.map(productDTO, product);
		if (productDTO.getDisable() != null) {
			product.setDisable(productDTO.getDisable());
		}
		productRepository.save(product);
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true);
	}

	@Override
	public ResponseDTO<ProductDTO> disable(Long productId) {
		log.info(String.format("--- Se ha recibido una peticion para deshabilitar  producto, id recibido: %s ----",
				productId));
		Product product = isProductPresent(productId);
		product.setDisable(true);
		productRepository.save(product);
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true);
	}

	private Product isProductPresent(Long id) {
		Optional<Product> checkProduct = productRepository.findById(id);
		if (!checkProduct.isPresent()) {
			log.info("Error, producto no se encuentra registrado");
			throw new NotFoundException(Product.class.getSimpleName(), Long.toString(id));
		}
		return checkProduct.get();
	}

}
