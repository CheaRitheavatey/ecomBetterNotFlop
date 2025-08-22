package com.example.localmarket.config;

import com.example.localmarket.entity.Category;
import com.example.localmarket.entity.Product;
import com.example.localmarket.entity.Province;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.ProductRepository;
import com.example.localmarket.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class DataInitializer {
    @Bean
    public List<Product> sampleProducts() {
        User maria = new User();
        maria.setFullname("Maria");
        maria.setProvince(Province.PHNOM_PENH);
        maria.setPhoneNumber("1111111");

        User carlos = new User();
        carlos.setProvince(Province.TAKEO);
        carlos.setFullname("Carlos");
        carlos.setPhoneNumber("2222222");

        User ana = new User();
        ana.setFullname("Ana");
        ana.setPhoneNumber("3333333");
        ana.setProvince(Province.SIEM_REAP);

        User miguel = new User();
        miguel.setFullname("Miguel");
        miguel.setPhoneNumber("4444444");
        miguel.setProvince(Province.KAMPOT);

        User rosa = new User();
        rosa.setFullname("Rosa");
        rosa.setPhoneNumber("5555555");
        rosa.setProvince(Province.SIEM_REAP);

        User pedro = new User();
        pedro.setFullname("Pedro");
        pedro.setPhoneNumber("6666666");
        pedro.setProvince(Province.TAKEO);


        return List.of(
                new Product( 1l,"Organic Honey",
                        "Pure wildflower honey from local beekeepers. Rich flavor and natural sweetness.",
                        "https://placehold.co/600x400",15.0, Province.SIEM_REAP, Category.FOOD_AND_BEVERAGE,maria,3.5,"contact_number"),
                new Product( 2l,"Handwoven Scarf",
                                    "Beautiful traditional scarf made with locally sourced wool. Unique patterns.",
                                    "https://placehold.co/600x400",32.0, Province.TAKEO, Category.CLOTHES,carlos,3.0,"contact_number"),
                new Product( 3l,"Ceramic Vase",
                                                    "Hand-crafted ceramic vase with traditional glazing techniques. Perfect for home decor.",
                                                    "https://placehold.co/600x400",45.0, Province.PHNOM_PENH, Category.GIFTS,ana,3.8,"contact_number"),
                new Product( 4l,"Fresh Coffee Beans",
                                                                    "Single-origin coffee beans grown in high altitude. Medium roast with chocolate notes.",
                                                                    "https://placehold.co/600x400",18.0, Province.KAMPOT, Category.FOOD_AND_BEVERAGE,ana,3.8,"contact_number")

        );
    }
}
