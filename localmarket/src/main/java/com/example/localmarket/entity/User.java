package com.example.localmarket.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    // data field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false, unique = true)
    private String phoneNumber; // for telegram login

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Province province;

    private Double rating;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products;
}
