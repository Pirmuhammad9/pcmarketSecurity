package com.company.lesson3task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private Double price;
    private List<Integer> products;
}
