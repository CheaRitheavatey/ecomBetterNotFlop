package com.example.localmarket.dto;


import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    // data field
    private Long id;
    private String name;
    private String description;
    private String imgurl;
    private Double price;
    private String sellerName;
    private Province province;
    private Category category;
    private Double rating;
    private String contactNumber;
}
