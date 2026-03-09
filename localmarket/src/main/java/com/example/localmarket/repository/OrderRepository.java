package com.example.localmarket.repository;

import com.example.localmarket.entity.Order;
import com.example.localmarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyerOrderByCreatedAtDesc(User buyer);
}
