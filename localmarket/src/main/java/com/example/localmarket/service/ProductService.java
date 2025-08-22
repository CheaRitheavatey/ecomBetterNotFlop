package com.example.localmarket.service;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // find all product
    public List<Product> finaAll() {
        return  productRepository.findAll();
    }

    // find by category
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    // find by search
    public List<Product> searchByName(String searchTerm) {
        return productRepository.searchByName(searchTerm);
    }

    // save
    public Product save(ProductDTO productDTO) {
        Product product = new Product(productDTO.getId(),productDTO.getName(), productDTO.getDescription(),productDTO.getImgUrl(), productDTO.getPrice(), productDTO.getProvinceDTO(), productDTO.getCategoryDTO(),productDTO.getUserDTO(), productDTO.getRating(), productDTO.getContact());
//

        return productRepository.save(product);

    }

}
