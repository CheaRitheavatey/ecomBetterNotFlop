package com.example.localmarket.repository;

import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByFullname(String fullname);
}
