package com.scrummers.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrummers.shop.model.ProductSales;

@Repository
public interface ProductSalesRepository extends JpaRepository<ProductSales, Long> {

}
