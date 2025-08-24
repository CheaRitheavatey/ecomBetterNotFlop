package com.example.localmarket.service;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.ProductRepository;
import com.example.localmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // -----------------------------
    // Public methods (CRUD)
    // -----------------------------

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return toDTO(product);
    }

    public List<ProductDTO> findByCategory(Category category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> searchByName(String searchTerm) {
        return productRepository.searchByName(searchTerm)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = toEntity(dto);
        Product saved = productRepository.save(product);
        return toDTO(saved);
    }

    // -----------------------------
    // Mapping methods
    // -----------------------------

    // Entity → DTO
    private ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImgurl(product.getImgurl());
        dto.setPrice(product.getPrice());
        dto.setProvince(product.getProvince());
        dto.setCategory(product.getCategory());
        dto.setRating(product.getRating());

        // Only seller name
        dto.setSellerName(product.getSeller().getFullname());

        return dto;
    }

    // DTO → Entity
    private Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImgurl(dto.getImgurl());
        product.setPrice(dto.getPrice());
        product.setProvince(dto.getProvince());
        product.setCategory(dto.getCategory());
        product.setRating(dto.getRating());

        // Assign seller from user repository
        User seller = userRepository.findUserByFullname(dto.getSellerName())
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        product.setSeller(seller);

        return product;
    }
}
