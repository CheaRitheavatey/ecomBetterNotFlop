package com.example.localmarket.repository;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductDTO> findByCategory(Category category);
    List<ProductDTO> searchByName(@Param("searchTerm") String searchTerm);
}
