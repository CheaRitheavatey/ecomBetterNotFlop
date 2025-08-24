package com.example.localmarket.service;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.User;
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
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImgUrl(productDTO.getImgUrl());
        product.setPrice(productDTO.getPrice());
        product.setProvince(productDTO.getProvinceDTO());
        product.setCategory(productDTO.getCategoryDTO());

        // Convert UserDTO → User
        User seller = new User();
        seller.setId();
        seller.setFullname(productDTO.getName());
        seller.setPhoneNumber(productDTO.getContact());
        seller.setPassword(productDTO.get());
        seller.setProvince(productDTO.getProvinceDTO());
        seller.setRating(productDTO.getRating());

        product.setSeller(seller);
        product.setRating(productDTO.getRating());
        product.setContact(productDTO.getContact());

        return productRepository.save(product);
    }

}
