package com.prince.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prince.productservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
