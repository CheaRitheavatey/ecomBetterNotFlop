package com.example.localmarket.repository;

import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByFullname(String fullname);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByTelegramChatId(Long chatId);
    boolean existsByPhoneNumber(String phoneNumber);
}
