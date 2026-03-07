package com.example.localmarket.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private String productImageUrl;
    private Integer quantity;
    private Double priceAtPurchase;
}
