package com.example.localmarket.controller;

import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.Role;
import com.example.localmarket.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() { return "auth/login"; }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("provinces", com.example.localmarket.entity.Province.values());
        model.addAttribute("roles", new Role[]{Role.ROLE_BUYER, Role.ROLE_SELLER});
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result,
                           RedirectAttributes ra, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("provinces", com.example.localmarket.entity.Province.values());
            model.addAttribute("roles", new Role[]{Role.ROLE_BUYER, Role.ROLE_SELLER});
            return "auth/register";
        }
        try {
            userService.register(userDTO);
            ra.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("provinces", com.example.localmarket.entity.Province.values());
            model.addAttribute("roles", new Role[]{Role.ROLE_BUYER, Role.ROLE_SELLER});
            return "auth/register";
        }
    }
}
