package com.example.localmarket.service;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.*;
import com.example.localmarket.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public List<ProductDTO> getWishlist(User user) {
        return wishlistRepository.findByUser(user).stream()
                .map(w -> productService.toDTO(w.getProduct()))
                .collect(Collectors.toList());
    }

    public boolean isWishlisted(User user, Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return false;
        return wishlistRepository.existsByUserAndProduct(user, product);
    }

    @Transactional
    public boolean toggleWishlist(User user, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return wishlistRepository.findByUserAndProduct(user, product).map(w -> {
            wishlistRepository.delete(w); return false;
        }).orElseGet(() -> {
            wishlistRepository.save(WishlistItem.builder().user(user).product(product).build());
            return true;
        });
    }
}

