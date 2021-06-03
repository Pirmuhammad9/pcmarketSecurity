package com.company.lesson3task1.repository;

import com.company.lesson3task1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByRegion(String region);
    boolean existsByRegionAndIdNot(String region, Integer id);
}
