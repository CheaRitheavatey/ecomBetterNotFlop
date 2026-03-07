package com.example.localmarket.controller;

import com.example.localmarket.entity.Category;
import com.example.localmarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featuredProducts", productService.getTopRated(8));
        model.addAttribute("newestProducts", productService.getNewest(8));
        model.addAttribute("categories", Category.values());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(@RequestParam(required = false) String category,
                       @RequestParam(required = false) String province,
                       @RequestParam(required = false) Double minPrice,
                       @RequestParam(required = false) Double maxPrice,
                       Model model) {
        var cat = category != null ? com.example.localmarket.entity.Category.valueOf(category) : Category.ALL_CATEGORY;
        var prov = province != null && !province.isBlank() ? com.example.localmarket.entity.Province.valueOf(province) : null;
        model.addAttribute("products", productService.filter(cat, prov, minPrice, maxPrice));
        model.addAttribute("categories", Category.values());
        model.addAttribute("provinces", com.example.localmarket.entity.Province.values());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedProvince", province);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "shop";
    }

    @GetMapping("/search")
    public String search(@RequestParam("q") String query, Model model) {
        model.addAttribute("products", productService.search(query));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model,
                                org.springframework.security.core.Authentication auth) {
        model.addAttribute("product", productService.getProductById(id));
        if (auth != null) {
            var user = getUserFromAuth(auth);
            model.addAttribute("isWishlisted", wishlistService.isWishlisted(user, id));
        }
        return "product-detail";
    }

    private final com.example.localmarket.service.WishlistService wishlistService;
    private final com.example.localmarket.service.UserService userService;

    private com.example.localmarket.entity.User getUserFromAuth(org.springframework.security.core.Authentication auth) {
        return userService.getCurrentUser(auth.getName());
    }
}
