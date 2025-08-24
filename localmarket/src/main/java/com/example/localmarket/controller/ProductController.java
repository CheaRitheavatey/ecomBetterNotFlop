package com.example.localmarket.controller;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    // GET
    @GetMapping
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProducts();
    }


    @GetMapping(path = "/category/{category}")
    public List<ProductDTO> getProductByCategory(@PathVariable Category category) {
        return productService.findByCategory(category);
    }

    @GetMapping(path = "/search")
    public List<ProductDTO> getProductBySearch(@RequestParam("q") String searchTerm) {
        return productService.searchByName(searchTerm);
    }

    @GetMapping(path = "/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // POST
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO dto) {
        return productService.createProduct(dto);
    }
}
