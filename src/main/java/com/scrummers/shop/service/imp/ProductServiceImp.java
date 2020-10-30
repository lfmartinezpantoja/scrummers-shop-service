package com.scrummers.shop.service.imp;

import static com.scrummers.shop.helpers.Constants.SUCCESFULLY_REQUEST_LOG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.scrummers.shop.dto.MultimediaResourceDTO;
import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.exception.CustomException;
import com.scrummers.shop.exception.NotFoundException;
import com.scrummers.shop.exception.error.Error;
import com.scrummers.shop.model.MultimediaResource;
import com.scrummers.shop.model.Product;
import com.scrummers.shop.repository.MultimediaResourceRepository;
import com.scrummers.shop.repository.ProductRepository;
import com.scrummers.shop.service.ProductService;

import lombok.extern.java.Log;

@Log
@Service
public class ProductServiceImp implements ProductService {

	private final Path root = Paths.get("uploads");

	@Autowired
	Gson gson;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	MultimediaResourceRepository multimediaResourceRepository;

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
		log.info(String.format("--- Se ha recibido una peticion para actualizacion de producto, json recibido: %s ----",
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

	@Override
	public ResponseDTO<ProductDTO> addMultimediaResource(MultipartFile productFile, Long productId) throws IOException {
		log.info("--- Se ha recibido una peticion para agregar recurso multimedia a producto----");
		MultimediaResource multimediaResource = new MultimediaResource();
		Product product = isProductPresent(productId);
		Optional<MultimediaResource> checkMultiMediaesource = multimediaResourceRepository
				.findByName(productFile.getOriginalFilename());
		if (checkMultiMediaesource.isPresent()) {
			throw new CustomException(Error.RESOURCE_ALREADY_EXIST.getCode(),
					String.format(Error.RESOURCE_ALREADY_EXIST.getDescription(),
							MultimediaResource.class.getSimpleName(), productFile.getOriginalFilename()),
					HttpStatus.BAD_REQUEST);
		}
		Files.copy(productFile.getInputStream(), this.root.resolve(productFile.getOriginalFilename()));
		multimediaResource.setName(productFile.getOriginalFilename());
		multimediaResource.setProduct(product);
		multimediaResource.setType(FilenameUtils.getExtension(productFile.getOriginalFilename()));
		multimediaResource.setUrl(this.root.resolve(productFile.getOriginalFilename()).toString());
		multimediaResourceRepository.save(multimediaResource);
		return new ResponseDTO<>(true);
	}

	@Override
	public ResponseDTO<MultimediaResourceDTO> getMultimediaResource(String productFileName, Long productId)
			throws IOException {
		log.info("--- Se ha recibido una peticion para consultar recurso multimedia a producto----");
		Optional<MultimediaResource> multimediResource = multimediaResourceRepository
				.findByNameAndProductId(productFileName, productId);
		if (!multimediResource.isPresent()) {
			throw new NotFoundException(MultimediaResource.class.getSimpleName(), productFileName);
		}

		Path file = root.resolve(productFileName);
		Resource resource = new UrlResource(file.toUri());
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true,
				new MultimediaResourceDTO(multimediResource.get().getName(), resource.getURL().toString()));
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
