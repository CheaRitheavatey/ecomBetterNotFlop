package com.example.localmarket.dto;

import com.example.localmarket.entity.Province;
import com.example.localmarket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    // data field
    private Long id;
    private String fullname;
    private String phoneNumber;
    private String password;
    private Province province;
    private List<ProductDTO> productDTOS;
}
