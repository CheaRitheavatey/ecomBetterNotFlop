package com.example.localmarket.service;

import com.example.localmarket.dto.*;
import com.example.localmarket.entity.*;
import com.example.localmarket.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;

    public List<OrderDTO> getUserOrders(User user) {
        return orderRepository.findByBuyerOrderByCreatedAtDesc(user)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        return toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @Transactional
    public OrderDTO checkout(User user, CheckoutDTO checkoutDTO) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        if (cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

        Order order = new Order();
        order.setBuyer(user);
        order.setDeliveryAddress(checkoutDTO.getDeliveryAddress());
        order.setPaymentMethod(checkoutDTO.getPaymentMethod());

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            if (product.getStockQuantity() < cartItem.getQuantity())
                throw new RuntimeException("Insufficient stock for: " + product.getName());
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);
            return OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        order.setTotalAmount(orderItems.stream()
                .mapToDouble(i -> i.getPriceAtPurchase() * i.getQuantity()).sum());

        Order saved = orderRepository.save(order);
        cartService.clearCart(user);
        return toDTO(saved);
    }

    @Transactional
    public OrderDTO updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return toDTO(orderRepository.save(order));
    }

    private OrderDTO toDTO(Order o) {
        OrderDTO dto = new OrderDTO();
        dto.setId(o.getId());
        dto.setBuyerName(o.getBuyer().getFullname());
        dto.setStatus(o.getStatus());
        dto.setTotalAmount(o.getTotalAmount());
        dto.setDeliveryAddress(o.getDeliveryAddress());
        dto.setPaymentMethod(o.getPaymentMethod());
        dto.setTrackingNumber(o.getTrackingNumber());
        dto.setCreatedAt(o.getCreatedAt());
        if (o.getItems() != null) {
            dto.setItems(o.getItems().stream().map(item -> {
                OrderItemDTO i = new OrderItemDTO();
                i.setProductId(item.getProduct().getId());
                i.setProductName(item.getProduct().getName());
                i.setProductImageUrl(item.getProduct().getMainImageUrl());
                i.setQuantity(item.getQuantity());
                i.setPriceAtPurchase(item.getPriceAtPurchase());
                return i;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}
