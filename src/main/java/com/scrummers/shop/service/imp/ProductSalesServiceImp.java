package com.scrummers.shop.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.scrummers.shop.dto.ProductDTO;
import com.scrummers.shop.dto.ProductSalesDTO;
import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.service.ProductSalesService;
import com.scrummers.shop.service.ProductService;
import com.scrummers.shop.exception.CustomException;
import com.scrummers.shop.exception.error.Error;
import com.scrummers.shop.model.Product;
import com.scrummers.shop.model.ProductSales;
import com.scrummers.shop.model.Shop;
import com.scrummers.shop.repository.ProductSalesRepository;

@Service
public class ProductSalesServiceImp implements ProductSalesService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductService productService;

	@Autowired
	ProductSalesRepository productSalesRepository;

	@Override
	public ResponseDTO<ProductDTO> registSale(ProductSalesDTO productSalesDTO) {
		ProductDTO productDTO = productService.get(productSalesDTO.getProductId()).getData();
		ProductSales productSales = new ProductSales();
		if (!productDTO.getShopId().equals(productSalesDTO.getShopId())) {
			throw new CustomException(Error.PRODUCT_NOT_AVAILABLE_IN_SHOP.getCode(),
					String.format(Error.PRODUCT_NOT_AVAILABLE_IN_SHOP.getDescription(),
							Long.toString(productSalesDTO.getProductId()), Long.toString(productSalesDTO.getShopId())),
					HttpStatus.BAD_REQUEST);
		}
		if (productSales.getQuantity() > productDTO.getQuantity()) {
			throw new CustomException(Error.QUANTITY_NOT_AVAILABLE.getCode(), String
					.format(Error.QUANTITY_NOT_AVAILABLE.getDescription(), Long.toString(productSalesDTO.getShopId())),
					HttpStatus.BAD_REQUEST);
		}
		productDTO.setQuantity(productDTO.getQuantity() - productSalesDTO.getQuantity());
		productDTO.setShopId(productSalesDTO.getShopId());
		productSales.setProduct(new Product(productSalesDTO.getProductId()));
		productSales.setShop(new Shop(productSalesDTO.getShopId()));
		productSalesRepository.save(productSales);
		productService.update(productDTO);
		return new ResponseDTO<>(true);
	}

}
