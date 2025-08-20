package com.example.localmarket.controller;

import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.User;
import com.example.localmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    @Autowired
    private UserService userService;

    // GET
    @GetMapping
    public List<User> getAllUser() {
        return userService.findAll();
    }

    // POST
    @PostMapping
    public User createUser(@PathVariable UserDTO userDTO) {
        return userService.save(userDTO);
    }
}
