package com.example.localmarket.controller;

import com.example.localmarket.entity.User;
import com.example.localmarket.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String myOrders(Authentication auth, Model model) {
        User user = userService.getCurrentUser(auth.getName());
        model.addAttribute("orders", orderService.getUserOrders(user));
        return "orders";
    }

    @GetMapping("/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order-detail";
    }
}
