package com.example.localmarket.service;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.Province;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.ProductRepository;
import com.example.localmarket.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .filter(Product::isActive)
                .map(this::toDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return toDTO(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }

    public Product getProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<ProductDTO> getByCategory(Category category) {
        if (category == Category.ALL_CATEGORY) return getAllProducts();
        return productRepository.findByCategoryAndActiveTrue(category)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> search(String query) {
        return productRepository.searchByNameOrDescription(query)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> filter(Category category, Province province, Double minPrice, Double maxPrice) {
        Category cat = (category == Category.ALL_CATEGORY) ? null : category;
        return productRepository.filterProducts(cat, province, minPrice, maxPrice)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getTopRated(int limit) {
        return productRepository.findTopRated(PageRequest.of(0, limit))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getNewest(int limit) {
        return productRepository.findNewest(PageRequest.of(0, limit))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getSellerProducts(User seller) {
        return productRepository.findBySellerAndActiveTrue(seller)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO dto, User seller) {
        Product product = toEntity(dto);
        product.setSeller(seller);
        product.setProvince(seller.getProvince());
        return toDTO(productRepository.save(product));
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO dto, User seller) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!product.getSeller().getId().equals(seller.getId()))
            throw new RuntimeException("Unauthorized");
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(dto.getCategory());
        if (dto.getImageUrls() != null && !dto.getImageUrls().isEmpty())
            product.setImgurl(dto.getImageUrls());
        return toDTO(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id, User seller) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!product.getSeller().getId().equals(seller.getId()))
            throw new RuntimeException("Unauthorized");
        product.setActive(false);
        productRepository.save(product);
    }

    @Transactional
    public void updateRating(Product product) {
        if (product.getReviews() == null || product.getReviews().isEmpty()) return;
        double avg = product.getReviews().stream()
                .mapToInt(r -> r.getRating()).average().orElse(0.0);
        product.setRating(Math.round(avg * 10.0) / 10.0);
        product.setReviewCount(product.getReviews().size());
        productRepository.save(product);
    }

    public ProductDTO toDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());
        dto.setStockQuantity(p.getStockQuantity());
        dto.setImageUrls(p.getImgurl());
        dto.setSellerName(p.getSeller().getFullname());
        dto.setSellerId(p.getSeller().getId());
        dto.setProvince(p.getProvince());
        dto.setCategory(p.getCategory());
        dto.setRating(p.getRating());
        dto.setReviewCount(p.getReviewCount());
        dto.setActive(p.isActive());
        dto.setCreatedAt(p.getCreatedAt());
        return dto;
    }

    private Product toEntity(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setStockQuantity(dto.getStockQuantity() != null ? dto.getStockQuantity() : 0);
        p.setCategory(dto.getCategory());
        p.setImgurl(dto.getImageUrls() != null ? dto.getImageUrls() : List.of());
        return p;
    }
}
