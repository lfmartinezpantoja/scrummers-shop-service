package com.scrummers.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrummers.shop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public Optional<Product> findByName(String name);

}
