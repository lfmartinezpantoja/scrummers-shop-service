package com.scrummers.shop.service.imp;

import java.util.Optional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.exception.CustomException;
import com.scrummers.shop.exception.NotFoundException;
import com.scrummers.shop.exception.error.Error;
import com.scrummers.shop.model.Product;
import com.scrummers.shop.repository.ProductRepository;
import com.scrummers.shop.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseDTO<ProductDTO> create(ProductDTO productDTO) {
		Product product = new Product();
		modelMapper.map(productDTO, product);
		productRepository.save(product);
		return new ResponseDTO<>(true);
	}

	@Override
	public ResponseDTO<ProductDTO> get(Long productId) {
		ProductDTO productDTO = new ProductDTO();
		Product product = isProductPresent(productId);
		if (product.isDisable()) {
			throw new CustomException(Error.RESOURSE_IS_DISABLE.getCode(),
					String.format(Error.RESOURSE_IS_DISABLE.getDescription(), Product.class.getSimpleName(),
							Long.toString(productId)),
					HttpStatus.BAD_REQUEST);
		}
		modelMapper.map(product, productDTO);
		return new ResponseDTO<>(true, productDTO);
	}

	@Override
	public ResponseDTO<ProductDTO> update(ProductDTO productDTO) {
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		Product product = isProductPresent(productDTO.getId());
		modelMapper.map(productDTO, product);
		if (productDTO.getDisable() != null) {
			product.setDisable(productDTO.getDisable());
		}
		productRepository.save(product);
		return new ResponseDTO<>(true);
	}

	@Override
	public ResponseDTO<ProductDTO> disable(Long productId) {
		Product product = isProductPresent(productId);
		product.setDisable(true);
		productRepository.save(product);
		return new ResponseDTO<>(true);
	}

	private Product isProductPresent(Long id) {
		Optional<Product> checkProduct = productRepository.findById(id);
		if (!checkProduct.isPresent()) {
			throw new NotFoundException(Product.class.getSimpleName(), Long.toString(id));
		}
		return checkProduct.get();
	}

}
