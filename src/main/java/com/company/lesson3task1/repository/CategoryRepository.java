package com.company.lesson3task1.repository;

import com.company.lesson3task1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
