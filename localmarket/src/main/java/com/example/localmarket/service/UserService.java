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
        User user = new User();
        user.setFullname(userDTO.getFullname());
        user.setPassword(userDTO.getPassword());
        user.setProvince(userDTO.getProvince());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return userRepository.save(user);
    }
}
