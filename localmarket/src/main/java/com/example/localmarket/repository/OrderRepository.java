package com.example.localmarket.repository;

import com.example.localmarket.entity.Order;
import com.example.localmarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyerOrderByCreatedAtDesc(User buyer);
}
