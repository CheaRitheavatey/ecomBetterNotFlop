package com.example.localmarket.config;
import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.*;
import com.example.localmarket.repository.*;
import com.example.localmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepo, ProductRepository productRepo, UserService userService) {
        return args -> {
            if (userRepo.count() > 0) return;

            // Admin
            UserDTO admin = new UserDTO();
            admin.setFullname("Admin");
            admin.setPhoneNumber("0000000000");
            admin.setPassword("admin123");
            admin.setProvince(Province.PHNOM_PENH);
            admin.setRole(Role.ROLE_ADMIN);
            userService.register(admin);

            // Seller 1
            UserDTO s1 = new UserDTO();
            s1.setFullname("Sophea Kim");
            s1.setPhoneNumber("0123456789");
            s1.setPassword("seller123");
            s1.setProvince(Province.SIEM_REAP);
            s1.setRole(Role.ROLE_SELLER);
            s1.setShopName("Sophea's Kitchen");
            s1.setShopDescription("Traditional Khmer recipes");
            userService.register(s1);

            // Seller 2
            UserDTO s2 = new UserDTO();
            s2.setFullname("Dara Chan");

            s2.setPhoneNumber("0987654321");
            s2.setPassword("seller123");
            s2.setProvince(Province.KAMPOT);
            s2.setRole(Role.ROLE_SELLER);
            s2.setShopName("Dara Crafts");
            s2.setShopDescription("Handmade Cambodian crafts");
            userService.register(s2);

            // Buyer
            UserDTO buyer = new UserDTO();
            buyer.setFullname("Maly Pich");
            buyer.setPhoneNumber("0112233445");
            buyer.setPassword("buyer123");
            buyer.setProvince(Province.PHNOM_PENH);
            buyer.setRole(Role.ROLE_BUYER);
            userService.register(buyer);

            User seller1 = userRepo.findByPhoneNumber("0123456789").get();
            User seller2 = userRepo.findByPhoneNumber("0987654321").get();

            // Products
            List<Product> products = List.of(
                    Product.builder().name("Kampot Pepper").description("Famous Kampot pepper, hand-harvested. Perfect for cooking or as a gourmet gift.")
                            .price(8.50).stockQuantity(50).category(Category.FOOD_AND_BEVERAGE).province(Province.KAMPOT)
                            .imgurl(List.of("https://images.unsplash.com/photo-1615485500704-8e990f9900f7?w=600")).seller(seller1).rating(4.8).reviewCount(24).active(true).build(),
                    Product.builder().name("Silk Scarf").description("Traditional Khmer silk scarf, hand-woven with intricate patterns.")
                            .price(35.00).stockQuantity(20).category(Category.CLOTHES).province(Province.SIEM_REAP)
                            .imgurl(List.of("https://images.unsplash.com/photo-1601924921557-45e6dea0a157?w=600")).seller(seller1).rating(4.6).reviewCount(12).active(true).build(),
                    Product.builder().name("Organic Honey").description("Pure wildflower honey from Takeo province beekeepers.")
                            .price(12.00).stockQuantity(30).category(Category.FOOD_AND_BEVERAGE).province(Province.TAKEO)
                            .imgurl(List.of("https://images.unsplash.com/photo-1587049352846-4a222e784d38?w=600")).seller(seller2).rating(4.7).reviewCount(18).active(true).build(),
                    Product.builder().name("Handwoven Basket").description("Beautiful rattan basket handcrafted by local artisans.")
                            .price(22.00).stockQuantity(15).category(Category.GIFTS).province(Province.KAMPOT)
                            .imgurl(List.of("https://images.unsplash.com/photo-1601758174493-e7d75e47e3e7?w=600")).seller(seller2).rating(4.5).reviewCount(9).active(true).build(),
                    Product.builder().name("Dried Mango").description("Sun-dried mango slices, no preservatives. Sweet and chewy!")
                            .price(6.00).stockQuantity(100).category(Category.FOOD_AND_BEVERAGE).province(Province.PHNOM_PENH)
                            .imgurl(List.of("https://images.unsplash.com/photo-1553279768-865429fa0078?w=600")).seller(seller1).rating(4.3).reviewCount(31).active(true).build(),
                    Product.builder().name("Silver Bracelet").description("Traditional Khmer silver bracelet with engraved patterns.")
                            .price(45.00).stockQuantity(10).category(Category.GIFTS).province(Province.SIEM_REAP)
                            .imgurl(List.of("https://images.unsplash.com/photo-1611591437281-460bfbe1220a?w=600")).seller(seller2).rating(4.9).reviewCount(7).active(true).build(),
                    Product.builder().name("Prahok Sauce").description("Traditional Khmer fermented fish paste sauce, perfect seasoning.")
                            .price(5.50).stockQuantity(60).category(Category.FOOD_AND_BEVERAGE).province(Province.SIEM_REAP)
                            .imgurl(List.of("https://images.unsplash.com/photo-1563379091339-03246963d51f?w=600")).seller(seller1).rating(4.2).reviewCount(15).active(true).build(),
                    Product.builder().name("Coconut Candle Set").description("Handmade coconut shell candles with lemongrass fragrance.")
                            .price(18.00).stockQuantity(25).category(Category.GIFTS).province(Province.KAMPOT)
                            .imgurl(List.of("https://images.unsplash.com/photo-1602028915047-37269d1a73f7?w=600")).seller(seller2).rating(4.7).reviewCount(22).active(true).build()
            );
            productRepo.saveAll(products);
            System.out.println("✅ Sample data initialized! Login: admin@localmarket.com / admin123");
        };
    }
}

