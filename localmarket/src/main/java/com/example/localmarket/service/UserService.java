package com.example.localmarket.service;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // -----------------------------
    // CRUD Methods
    // -----------------------------

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toDTO(user);
    }

    public UserDTO createUser(UserDTO dto) {
        User user = toEntity(dto);
        User saved = userRepository.save(user);
        return toDTO(saved);
    }

    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullname(dto.getFullname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        user.setProvince(dto.getProvince());
//        user.setRating(dto.getRating());

        User updated = userRepository.save(user);
        return toDTO(updated);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // -----------------------------
    // Mapping Methods
    // -----------------------------

    // Entity -> DTO
    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFullname(user.getFullname());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setProvince(user.getProvince());
//        dto.setRating(user.getRating());

        // Convert products to ProductDTO
        List<ProductDTO> productDTOs = user.getProducts()
                .stream()
                .map(p -> {
                    ProductDTO pdto = new ProductDTO();
                    pdto.setId(p.getId());
                    pdto.setName(p.getName());
                    pdto.setPrice(p.getPrice());
                    pdto.setImgurl(p.getImgurl());
                    pdto.setSellerName(user.getFullname()); // only seller name
                    return pdto;
                })
                .collect(Collectors.toList());

        dto.setProduct(productDTOs);
        return dto;
    }

    // DTO -> Entity
    private User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFullname(dto.getFullname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        user.setProvince(dto.getProvince());
//        user.setRating(dto.getRating());
        // Products are not created here (handled separately in ProductService)
        return user;
    }
}
