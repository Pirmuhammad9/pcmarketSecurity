package com.company.lesson3task1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private String info;

    private Double price;

    private Integer warrenty;

    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment attachment;

}
