package com.scrummers.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrummers.shop.model.MultimediaResource;

public interface MultimediaResourceRepository extends JpaRepository<MultimediaResource, Long> {

	public Optional<MultimediaResource> findByNameAndProductId(String productFilename, Long productId);
	
	public Optional<MultimediaResource> findByName(String productFilename);
}
