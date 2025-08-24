package com.example.localmarket.config;

import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.Province;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.ProductRepository;
import com.example.localmarket.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(UserRepository userRepository, ProductRepository productRepository) {
        return args -> {
            // Create Users
            User maria = new User();
            maria.setFullname("Maria");
            maria.setProvince(Province.PHNOM_PENH);
            maria.setPhoneNumber("000000000");
            maria.setPassword("1111111");
            userRepository.save(maria);



            // Create Products
            Product honey = new Product(null, "Organic Honey", "Pure wildflower honey", "https://placehold.co/600x400", 12.00, maria, Province.TAKEO, Category.FOOD_AND_BEVERAGE, 3.4);
            productRepository.save(honey);

            System.out.println("✅ Sample data initialized!");

            // --------- Print Users ----------
            System.out.println("===== All Users =====");
            List<User> users = userRepository.findAll();
            for (User user : users) {
                System.out.println("User: " + user.getFullname() + ", Phone: " + user.getPhoneNumber()
                        + ", Province: " + user.getProvince() + ", Password: " + user.getPassword());
            }

            // --------- Print Products ----------
            System.out.println("===== All Products =====");
            List<Product> products = productRepository.findAll();
            for (Product product : products) {
                System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice()
                        + ", Province: " + product.getProvince() + ", Category: " + product.getCategory()
                        + ", Seller: " + product.getSeller().getFullname());
            }

            System.out.println("✅ Sample data initialized and tested!");

        };


    }


}
