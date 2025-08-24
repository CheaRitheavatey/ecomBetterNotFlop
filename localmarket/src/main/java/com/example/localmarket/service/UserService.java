package com.example.localmarket.service;

import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

//    public List<User> findById(Long id) {
//        return userRepository.findAllById(id);
//    }

    public User save(UserDTO userDTO) {
        User seller = new User();
        seller.setId(userDTO.getId());
        seller.setFullname(userDTO.getFullname());
        seller.setPhoneNumber(userDTO.getPhoneNumber());
        seller.setPassword(userDTO.getPassword());
        seller.setProvince(userDTO.getProvince());
        seller.setRating(userDTO.getRating());

        return userRepository.save(seller);
    }
}
