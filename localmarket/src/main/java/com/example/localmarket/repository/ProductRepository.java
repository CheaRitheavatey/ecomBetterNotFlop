package com.example.localmarket.repository;

import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProvinceAndCategory(Province province, Category category);
}
