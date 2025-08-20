package com.example.localmarket.controller;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // GET
    public List<Product> getAllProduct() {
        return productService.finaAll();
    }


    @GetMapping(path = "/category/{category}")
    public List<Product> getProductByCategory(@PathVariable Category category) {
        return productService.findByCategory(category);
    }

    @GetMapping(path = "/search")
    public List<Product> getProductBySearch(@PathVariable String searchTerm) {
        return productService.searchByName(searchTerm);
    }

    // POST
    @PostMapping
    public Product createProduct(@PathVariable ProductDTO productDTO) {
        return productService.save(productDTO);
    }
}
