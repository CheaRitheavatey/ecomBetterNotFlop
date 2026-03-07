package com.example.localmarket.dto;

import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.Province;
import com.example.localmarket.entity.Role;
import com.example.localmarket.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UserDTO {
    // data field
    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullname;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private Province province;
    private Role role;
    private String shopName;
    private String shopDescription;
    private String profileImageUrl;
    private boolean telegramRegistered;
    private List<ProductDTO> products;
    private LocalDateTime createdAt;
}
