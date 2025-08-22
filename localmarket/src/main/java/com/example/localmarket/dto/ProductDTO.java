package com.example.localmarket.dto;


import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    // data field
    private Long id;
    private String name;
    private String description;
    private double price;
    private Province provinceDTO;
    private Category categoryDTO;
    private UserDTO userDTO;
    private Double rating;
    private String contact;
    private String imgUrl;
}
