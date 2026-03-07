package com.example.localmarket.controller;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.*;
import com.example.localmarket.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;
    private final UserService userService;
    private final ImageService imageService;

    private User getUser(Authentication auth) {
        return userService.getCurrentUser(auth.getName());
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        User user = getUser(auth);
        model.addAttribute("products", productService.getSellerProducts(user));
        model.addAttribute("user", userService.toDTO(user));
        return "seller/dashboard";
    }

    @GetMapping("/product/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", Category.values());
        return "seller/product-form";
    }

    @PostMapping("/product/new")
    public String createProduct(@Valid @ModelAttribute ProductDTO product,
                                BindingResult result,
                                @RequestParam(value = "images", required = false) List<MultipartFile> images,
                                Authentication auth, RedirectAttributes ra, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", Category.values());
            return "seller/product-form";
        }
        try {
            User seller = getUser(auth);
            List<String> imageUrls = new ArrayList<>();
            if (images != null) {
                for (MultipartFile img : images) {
                    if (!img.isEmpty()) imageUrls.add(imageService.uploadImage(img));
                }
            }
            product.setImageUrls(imageUrls);
            productService.createProduct(product, seller);
            ra.addFlashAttribute("success", "Product listed successfully!");
            return "redirect:/seller/dashboard";
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/seller/product/new";
        }
    }

    @GetMapping("/product/edit/{id}")
    public String editForm(@PathVariable Long id, Authentication auth, Model model) {
        ProductDTO product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", Category.values());
        return "seller/product-form";
    }

    @PostMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute ProductDTO product,
                                BindingResult result,
                                @RequestParam(value = "images", required = false) List<MultipartFile> images,
                                Authentication auth, RedirectAttributes ra) {
        if (result.hasErrors()) return "seller/product-form";
        try {
            User seller = getUser(auth);
            List<String> imageUrls = new ArrayList<>(product.getImageUrls() != null ? product.getImageUrls() : List.of());
            if (images != null) {
                for (MultipartFile img : images) {
                    if (!img.isEmpty()) imageUrls.add(imageService.uploadImage(img));
                }
            }
            product.setImageUrls(imageUrls);
            productService.updateProduct(id, product, seller);
            ra.addFlashAttribute("success", "Product updated!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/seller/dashboard";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Authentication auth, RedirectAttributes ra) {
        productService.deleteProduct(id, getUser(auth));
        ra.addFlashAttribute("success", "Product removed.");
        return "redirect:/seller/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Authentication auth, Model model) {
        model.addAttribute("user", userService.toDTO(getUser(auth)));
        model.addAttribute("provinces", Province.values());
        return "seller/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute com.example.localmarket.dto.UserDTO dto,
                                Authentication auth, RedirectAttributes ra) {
        try {
            userService.updateProfile(getUser(auth).getId(), dto);
            ra.addFlashAttribute("success", "Profile updated!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/seller/profile";
    }
}
