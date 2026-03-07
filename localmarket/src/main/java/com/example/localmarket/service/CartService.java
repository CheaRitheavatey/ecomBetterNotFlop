package com.example.localmarket.service;

import com.example.localmarket.dto.CartItemDTO;
import com.example.localmarket.entity.CartItem;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.CartItemRepository;
import com.example.localmarket.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public List<CartItemDTO> getCart(User user) {
        return cartItemRepository.findByUser(user)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public double getCartTotal(User user) {
        return getCart(user).stream().mapToDouble(CartItemDTO::getSubtotal).sum();
    }

    public int getCartCount(User user) {
        return cartItemRepository.findByUser(user).stream()
                .mapToInt(CartItem::getQuantity).sum();
    }

    @Transactional
    public void addToCart(User user, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        cartItemRepository.findByUserAndProduct(user, product).ifPresentOrElse(
                item -> { item.setQuantity(item.getQuantity() + quantity); cartItemRepository.save(item); },
                () -> cartItemRepository.save(CartItem.builder().user(user).product(product).quantity(quantity).build())
        );
    }

    @Transactional
    public void updateQuantity(User user, Long itemId, int quantity) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (!item.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized");
        if (quantity <= 0) cartItemRepository.delete(item);
        else { item.setQuantity(quantity); cartItemRepository.save(item); }
    }

    @Transactional
    public void removeFromCart(User user, Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        if (!item.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized");
        cartItemRepository.delete(item);
    }

    @Transactional
    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }

    private CartItemDTO toDTO(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setProductImageUrl(item.getProduct().getMainImageUrl());
        dto.setProductPrice(item.getProduct().getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getProduct().getPrice() * item.getQuantity());
        dto.setStockQuantity(item.getProduct().getStockQuantity());
        return dto;
    }
}

