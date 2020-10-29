package com.scrummers.shop.service.imp;

import static com.scrummers.shop.helpers.Constants.SUCCESFULLY_REQUEST_LOG;

import java.util.Optional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.dto.ShopDTO;
import com.scrummers.shop.exception.CustomException;
import com.scrummers.shop.exception.NotFoundException;
import com.scrummers.shop.exception.error.Error;
import com.scrummers.shop.model.Shop;
import com.scrummers.shop.repository.ShopRepository;
import com.scrummers.shop.service.ShopService;

import lombok.extern.java.Log;

@Log
@Service
public class ShopServiceImp implements ShopService {

	@Autowired
	Gson gson;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ShopRepository shopRepository;

	@Override
	public ResponseDTO<ShopDTO> create(ShopDTO shopDTO) {
		log.info(String.format("--- Se ha recibido una peticion para crear  tienda, id recibido: %s ----",
				gson.toJson(shopDTO)));
		Shop shop = new Shop();
		modelMapper.map(shopDTO, shop);
		shopRepository.save(shop);
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true);
	}

	@Override
	public ResponseDTO<ShopDTO> get(Long shopId) {
		log.info(String.format("--- Se ha recibido una peticion para obtener  tienda, id recibido: %s ----", shopId));
		ShopDTO shopDTO = new ShopDTO();
		Shop shop = isShopPresent(shopId);
		if (shop.isDisable()) {
			throw new CustomException(Error.RESOURSE_IS_DISABLE.getCode(),
					String.format(Error.RESOURSE_IS_DISABLE.getDescription(), Shop.class.getSimpleName(),
							Long.toString(shopId)),
					HttpStatus.BAD_REQUEST);
		}
		modelMapper.map(shop, shopDTO);
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true, shopDTO);
	}

	@Override
	public ResponseDTO<ShopDTO> update(ShopDTO shopDTO) {
		log.info(String.format("--- Se ha recibido una peticion para actualizar  tienda, json recibido: %s ----",
				gson.toJson(shopDTO)));
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		Shop shop = isShopPresent(shopDTO.getId());
		modelMapper.map(shopDTO, shop);
		if (shopDTO.getDisable() != null) {
			shop.setDisable(shopDTO.getDisable());
		}
		shopRepository.save(shop);
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true);
	}

	@Override
	public ResponseDTO<ShopDTO> disable(Long shopId) {
		log.info(String.format("--- Se ha recibido una peticion para deshabilitar  tienda, id recibido: %s ----",
				shopId));
		Shop shop = isShopPresent(shopId);
		shop.setDisable(true);
		shopRepository.save(shop);
		log.info(SUCCESFULLY_REQUEST_LOG);
		return new ResponseDTO<>(true);
	}

	private Shop isShopPresent(Long id) {
		Optional<Shop> checkShop = shopRepository.findById(id);
		if (!checkShop.isPresent()) {
			throw new NotFoundException(Shop.class.getSimpleName(), Long.toString(id));
		}
		return checkShop.get();
	}

}
