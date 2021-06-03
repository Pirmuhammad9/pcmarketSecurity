package com.company.lesson3task1.repository;

import com.company.lesson3task1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
