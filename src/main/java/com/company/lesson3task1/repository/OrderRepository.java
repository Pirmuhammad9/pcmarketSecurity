package com.company.lesson3task1.repository;

import com.company.lesson3task1.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Integer> {
}
