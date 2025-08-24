package com.example.localmarket.service;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // find all product
    public List<ProductDTO> getAllProduct() {
        return  productRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // find by category
    public List<ProductDTO> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    // find by search
    public List<ProductDTO> searchByName(String searchTerm) {
        return productRepository.searchByName(searchTerm);
    }

    // create product
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = toEntity(dto);
        return toDTO(productRepository.save(product));
    }

    // get product by id
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id).map(this::toDTO).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    // convert entity to dto
    private ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImgurl(),
                product.getPrice(),
                product.getSellerName(),
                product.getProvince(),
                product.getCategory(),
                product.getRating(),
                product.getContactNumber()
        );
    }

    // convert dto to entity
    private Product toEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getImgurl(),
                productDTO.getPrice(),
                productDTO.getSellerName(),
                productDTO.getProvince(),
                productDTO.getCategory(),
                productDTO.getRating(),
                productDTO.getContactNumber()
        );
    }

}
