package com.example.localmarket.repository;

import com.example.localmarket.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryAndActiveTrue(Category category);
    Page<Product> findByActiveTrue(Pageable pageable);
    List<Product> findBySellerAndActiveTrue(User seller);

    @Query("SELECT p FROM Product p WHERE p.active = true AND (LOWER(p.name) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%',:q,'%')))")
    List<Product> searchByNameOrDescription(@Param("q") String query);

    @Query("SELECT p FROM Product p WHERE p.active = true AND (:category IS NULL OR p.category = :category) AND (:province IS NULL OR p.province = :province) AND (:minPrice IS NULL OR p.price >= :minPrice) AND (:maxPrice IS NULL OR p.price <= :maxPrice) ORDER BY p.createdAt DESC")
    List<Product> filterProducts(@Param("category") Category category, @Param("province") Province province, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    @Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.rating DESC")
    List<Product> findTopRated(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true ORDER BY p.createdAt DESC")
    List<Product> findNewest(Pageable pageable);
}
