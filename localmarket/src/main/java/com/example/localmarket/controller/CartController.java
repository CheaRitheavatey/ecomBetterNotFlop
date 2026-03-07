package com.example.localmarket.controller;

import com.example.localmarket.dto.CheckoutDTO;
import com.example.localmarket.entity.User;
import com.example.localmarket.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;
    private final UserService userService;

    private User getUser(Authentication auth) {
        return userService.getCurrentUser(auth.getName());
    }

    @GetMapping
    public String viewCart(Authentication auth, Model model) {
        User user = getUser(auth);
        model.addAttribute("cartItems", cartService.getCart(user));
        model.addAttribute("total", cartService.getCartTotal(user));
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            Authentication auth, RedirectAttributes ra) {
        cartService.addToCart(getUser(auth), productId, quantity);
        ra.addFlashAttribute("success", "Added to cart!");
        return "redirect:/product/" + productId;
    }

    @PostMapping("/update/{itemId}")
    @ResponseBody
    public String updateQuantity(@PathVariable Long itemId, @RequestParam int quantity, Authentication auth) {
        cartService.updateQuantity(getUser(auth), itemId, quantity);
        return "ok";
    }

    @PostMapping("/remove/{itemId}")
    public String removeItem(@PathVariable Long itemId, Authentication auth) {
        cartService.removeFromCart(getUser(auth), itemId);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutPage(Authentication auth, Model model) {
        User user = getUser(auth);
        model.addAttribute("cartItems", cartService.getCart(user));
        model.addAttribute("total", cartService.getCartTotal(user));
        model.addAttribute("checkout", new CheckoutDTO());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@Valid @ModelAttribute CheckoutDTO checkout, Authentication auth, RedirectAttributes ra) {
        try {
            var order = orderService.checkout(getUser(auth), checkout);
            ra.addFlashAttribute("success", "Order placed! Order #" + order.getId());
            return "redirect:/orders/" + order.getId();
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/cart/checkout";
        }
    }
}
