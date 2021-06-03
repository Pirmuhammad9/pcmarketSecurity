package com.company.lesson3task1.repository;

import com.company.lesson3task1.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
}
