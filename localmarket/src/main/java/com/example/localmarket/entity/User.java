package com.example.localmarket.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "seller")
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
//    @JsonIgnore
    private List<Product> products ;
}
