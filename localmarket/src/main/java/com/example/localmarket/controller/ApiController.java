package com.example.localmarket.controller;

import com.example.localmarket.dto.*;
import com.example.localmarket.entity.*;
import com.example.localmarket.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ProductService productService;
    private final CartService cartService;
    private final ReviewService reviewService;
    private final WishlistService wishlistService;
    private final UserService userService;

    // Products
    @GetMapping("/products")
    public List<ProductDTO> getProducts(@RequestParam(required = false) String category,
                                        @RequestParam(required = false) String q) {
        if (q != null && !q.isBlank()) return productService.search(q);
        if (category != null) return productService.getByCategory(Category.valueOf(category));
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Cart
    @GetMapping("/cart")
    public ResponseEntity<?> getCart(Authentication auth) {
        User user = userService.getCurrentUser(auth.getName());
        return ResponseEntity.ok(Map.of("items", cartService.getCart(user), "total", cartService.getCartTotal(user)));
    }

    @PostMapping("/cart/add")
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> body, Authentication auth) {
        User user = userService.getCurrentUser(auth.getName());
        cartService.addToCart(user, Long.parseLong(body.get("productId").toString()),
                Integer.parseInt(body.get("quantity").toString()));
        return ResponseEntity.ok(Map.of("count", cartService.getCartCount(user)));
    }

    // Reviews
    @GetMapping("/products/{id}/reviews")
    public List<ReviewDTO> getReviews(@PathVariable Long id) {
        return reviewService.getProductReviews(id);
    }

    @PostMapping("/products/{id}/reviews")
    public ResponseEntity<?> addReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO dto, Authentication auth) {
        User user = userService.getCurrentUser(auth.getName());
        return ResponseEntity.ok(reviewService.addReview(user, id, dto));
    }

    // Wishlist
    @PostMapping("/wishlist/toggle/{productId}")
    public ResponseEntity<?> toggleWishlist(@PathVariable Long productId, Authentication auth) {
        User user = userService.getCurrentUser(auth.getName());
        boolean wishlisted = wishlistService.toggleWishlist(user, productId);
        return ResponseEntity.ok(Map.of("wishlisted", wishlisted));
    }

    @GetMapping("/wishlist")
    public List<ProductDTO> getWishlist(Authentication auth) {
        return wishlistService.getWishlist(userService.getCurrentUser(auth.getName()));
    }
}
