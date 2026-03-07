package com.example.localmarket.dto;

import com.example.localmarket.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String buyerName;
    private List<OrderItemDTO> items;
    private OrderStatus status;
    private Double totalAmount;
    private String deliveryAddress;
    private String paymentMethod;
    private String trackingNumber;
    private LocalDateTime createdAt;
}
