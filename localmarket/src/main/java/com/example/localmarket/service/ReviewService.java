package com.example.localmarket.service;

import com.example.localmarket.dto.ReviewDTO;
import com.example.localmarket.entity.*;
import com.example.localmarket.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public List<ReviewDTO> getProductReviews(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return reviewRepository.findByProductOrderByCreatedAtDesc(product)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ReviewDTO addReview(User user, Long productId, ReviewDTO dto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        reviewRepository.findByUserAndProduct(user, product)
                .ifPresent(r -> { throw new RuntimeException("Already reviewed this product"); });
        Review review = Review.builder()
                .user(user).product(product)
                .rating(dto.getRating()).comment(dto.getComment())
                .build();
        Review saved = reviewRepository.save(review);
        // Reload product with reviews and update rating
        Product reloaded = productRepository.findById(productId).get();
        productService.updateRating(reloaded);
        return toDTO(saved);
    }

    private ReviewDTO toDTO(Review r) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(r.getId());
        dto.setRating(r.getRating());
        dto.setComment(r.getComment());
        dto.setUserName(r.getUser().getFullname());
        dto.setProductId(r.getProduct().getId());
        dto.setCreatedAt(r.getCreatedAt());
        return dto;
    }
}

