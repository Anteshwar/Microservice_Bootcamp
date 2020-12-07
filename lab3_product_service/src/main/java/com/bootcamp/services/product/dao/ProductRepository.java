package com.bootcamp.services.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.services.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
