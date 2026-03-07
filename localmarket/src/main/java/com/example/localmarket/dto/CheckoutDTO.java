package com.example.localmarket.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckoutDTO {
    @NotBlank
    private String deliveryAddress;

    @NotBlank
    private String paymentMethod;
}
