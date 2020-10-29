package com.scrummers.shop.service;

import com.scrummers.shop.dto.ResponseDTO;
import com.scrummers.shop.dto.ShopDTO;

public interface ShopService {

	public ResponseDTO<ShopDTO> create(ShopDTO shopDTO);

	public ResponseDTO<ShopDTO> get(Long shopId);

	public ResponseDTO<ShopDTO> update(ShopDTO shopDTO);

	public ResponseDTO<ShopDTO> disable(Long shopId);
}
