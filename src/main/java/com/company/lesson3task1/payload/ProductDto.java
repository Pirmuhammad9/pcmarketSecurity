package com.company.lesson3task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private String info;
    private Double price;
    private Integer warrenty;
    private String catName;
    private String attachName;
    private Double size;
    private String contentType;
    private byte[] bytes;

}
