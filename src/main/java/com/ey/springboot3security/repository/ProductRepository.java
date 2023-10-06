package com.ey.springboot3security.repository;

import com.ey.springboot3security.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{


}