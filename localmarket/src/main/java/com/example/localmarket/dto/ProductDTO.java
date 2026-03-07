package com.example.localmarket.dto;


import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Province;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ProductDTO {
    // data field
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stockQuantity;

    private List<String> imageUrls;
    private String sellerName;
    private Long sellerId;
    private Province province;
    private Category category;
    private Double rating;
    private Integer reviewCount;
    private boolean active;
    private LocalDateTime createdAt;
}
