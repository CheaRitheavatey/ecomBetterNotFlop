package com.example.localmarket.service;

import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.Role;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public User getCurrentUser(String username) {
        return userRepository.findByPhoneNumber(username)
                        .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public UserDTO register(UserDTO dto) {
        if (userRepository.existsByPhoneNumber((dto.getPhoneNumber())))
            throw new RuntimeException("Phone number already registered");

        User user = new User();
        user.setFullname(dto.getFullname());
        user.setPhoneNumber(dto.getPhoneNumber());
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setProvince(dto.getProvince());
        user.setRole(dto.getRole() != null ? dto.getRole() : Role.ROLE_BUYER);
        user.setShopName(dto.getShopName());
        user.setShopDescription(dto.getShopDescription());
        user.setEnabled(true);
        return toDTO(userRepository.save(user));
    }

    @Transactional
    public UserDTO updateProfile(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFullname(dto.getFullname());
        if (dto.getPhoneNumber() != null) user.setPhoneNumber(dto.getPhoneNumber());
        user.setProvince(dto.getProvince());
        user.setShopName(dto.getShopName());
        user.setShopDescription(dto.getShopDescription());
//        if (dto.getPassword() != null && !dto.getPassword().isBlank())
//            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return toDTO(userRepository.save(user));
    }

    public UserDTO toDTO(User u) {
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setFullname(u.getFullname());
        dto.setPhoneNumber(u.getPhoneNumber());
        dto.setProvince(u.getProvince());
        dto.setRole(u.getRole());
        dto.setShopName(u.getShopName());
        dto.setShopDescription(u.getShopDescription());
        dto.setProfileImageUrl(u.getProfileImageUrl());
        dto.setTelegramRegistered(u.isTelegramRegistered());
        dto.setCreatedAt(u.getCreatedAt());
        return dto;
    }
}
