package com.scrummers.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrummers.shop.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

	Optional<Shop> findByAddress(String address);
}
