package com.example.localmarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imgurl;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String sellerName;

    @Column(nullable = false)
    private Province province;

    @Column(nullable = false)
    private Category category;

    private Double rating;

    @Column(nullable = false)
    private String contactNumber;
}