package com.bootcamp.services.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.services.product.model.ProductTag;

@Repository
public interface ProductTagsRepository extends JpaRepository<ProductTag, Integer> {

}
