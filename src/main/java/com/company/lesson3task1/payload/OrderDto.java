package com.company.lesson3task1.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String name;
    private String phoneNumber;
    private Double price;
    private String region;
    private String district;
    private String street;
    private List<Integer> products;
}
