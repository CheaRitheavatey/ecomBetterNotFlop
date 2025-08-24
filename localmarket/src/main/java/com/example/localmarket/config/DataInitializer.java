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

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepository, ProductRepository productRepository) {
        return args -> {
            User maria = new User();
            maria.setFullname("Maria");
            maria.setProvince(Province.PHNOM_PENH);
            maria.setPhoneNumber("000000000");
            maria.setPassword("1111111");
            userRepository.save(maria);

            User carlos = new User();
            carlos.setProvince(Province.TAKEO);
            carlos.setFullname("Carlos");
            carlos.setPhoneNumber("999999999");
            carlos.setPassword("2222222");
            userRepository.save(carlos);

            User ana = new User();
            ana.setFullname("Ana");
            ana.setPassword("3333333");
            ana.setPhoneNumber("888888");
            ana.setProvince(Province.SIEM_REAP);
            userRepository.save(ana);

            Product honey = new Product(null, "Organic Honey",
                    "Pure wildflower honey from local beekeepers. Rich flavor and natural sweetness.",
                    "https://placehold.co/600x400", 15.0,
                    Province.SIEM_REAP, Category.FOOD_AND_BEVERAGE,
                    maria, 3.5, "contact_number");
            productRepository.save(honey);

            Product scarf = new Product(null, "Handwoven Scarf",
                    "Beautiful traditional scarf made with locally sourced wool. Unique patterns.",
                    "https://placehold.co/600x400", 32.0,
                    Province.TAKEO, Category.CLOTHES,
                    carlos, 3.0, "contact_number");
            productRepository.save(scarf);

            Product vase = new Product(null, "Ceramic Vase",
                    "Hand-crafted ceramic vase with traditional glazing techniques. Perfect for home decor.",
                    "https://placehold.co/600x400", 45.0,
                    Province.PHNOM_PENH, Category.GIFTS,
                    ana, 3.8, "contact_number");
            productRepository.save(vase);

            Product coffee = new Product(null, "Fresh Coffee Beans",
                    "Single-origin coffee beans grown in high altitude. Medium roast with chocolate notes.",
                    "https://placehold.co/600x400", 18.0,
                    Province.KAMPOT, Category.FOOD_AND_BEVERAGE,
                    ana, 3.8, "contact_number");
            productRepository.save(coffee);

            System.out.println("✅ Sample data initialized!");
        };
    }
}
