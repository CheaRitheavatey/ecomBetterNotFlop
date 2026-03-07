package com.example.localmarket.bot;

import com.example.localmarket.dto.ProductDTO;
import com.example.localmarket.entity.*;
import com.example.localmarket.entity.User;
import com.example.localmarket.repository.UserRepository;
import com.example.localmarket.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SellerBot extends TelegramLongPollingBot {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ImageService imageService;

    @Value("${telegram.bot.username:localmarket_khBOT}")
    private String botUsername;

    @Value("${telegram.bot.token:YOUR_BOT_TOKEN}")
    private String botToken;

    @Value("${app.base-url:http://localhost:8080}")
    private String appBaseUrl;

    // State machine per chat
    private final Map<Long, BotSession> sessions = new ConcurrentHashMap<>();

    public SellerBot(UserRepository userRepository, UserService userService,
                     ProductService productService, ImageService imageService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.productService = productService;
        this.imageService = imageService;
    }

    @Override public String getBotUsername() { return botUsername; }
    @Override public String getBotToken() { return botToken; }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message msg = update.getMessage();
            long chatId = msg.getChatId();
            BotSession session = sessions.computeIfAbsent(chatId, k -> new BotSession());

            if (msg.hasText()) handleText(chatId, msg.getText(), session);
            else if (msg.hasPhoto()) handlePhoto(chatId, msg, session);
        }
    }

    private void handleText(long chatId, String text, BotSession session) {
        if (text.startsWith("/")) {
            handleCommand(chatId, text, session);
            return;
        }
        switch (session.state) {
            case WAITING_NAME -> { session.regName = text; session.state = BotState.WAITING_PHONE;
                send(chatId, "📱 Enter your phone number:"); }
            case WAITING_PHONE -> { session.regPhone = text; session.state = BotState.WAITING_PASSWORD;
                send(chatId, "🔒 Set a password (min 6 characters):"); }
            case WAITING_PASSWORD -> { session.regPassword = text; session.state = BotState.WAITING_PROVINCE;
                send(chatId, "📍 Enter your province:\nOptions: PHNOM_PENH, SIEM_REAP, KAMPOT, TAKEO, BATTAMBANG, KANDAL, KOMPONG_CHAM"); }
            case WAITING_PROVINCE -> {
                try {
                    session.regProvince = Province.valueOf(text.toUpperCase());
                    session.state = BotState.WAITING_SHOP_NAME;
                    send(chatId, "🏪 Enter your shop name:");
                } catch (Exception e) { send(chatId, "❌ Invalid province. Try again:"); }
            }
            case WAITING_SHOP_NAME -> { session.shopName = text; session.state = BotState.REGISTERED;
                completeRegistration(chatId, session); }
            case PRODUCT_NAME -> { session.productName = text; session.state = BotState.PRODUCT_DESC;
                send(chatId, "📝 Enter product description:"); }
            case PRODUCT_DESC -> { session.productDesc = text; session.state = BotState.PRODUCT_PRICE;
                send(chatId, "💰 Enter price (e.g., 12.50):"); }
            case PRODUCT_PRICE -> {
                try { session.productPrice = Double.parseDouble(text); session.state = BotState.PRODUCT_STOCK;
                    send(chatId, "📦 Enter stock quantity:"); }
                catch (Exception e) { send(chatId, "❌ Invalid price. Enter a number:"); }
            }
            case PRODUCT_STOCK -> {
                try { session.productStock = Integer.parseInt(text); session.state = BotState.PRODUCT_CATEGORY;
                    send(chatId, "🏷️ Enter category:\nFOOD_AND_BEVERAGE, CLOTHES, GIFTS, ELECTRONICS, HANDICRAFTS, BEAUTY"); }
                catch (Exception e) { send(chatId, "❌ Invalid quantity. Enter a number:"); }
            }
            case PRODUCT_CATEGORY -> {
                try { session.productCategory = Category.valueOf(text.toUpperCase());
                    session.state = BotState.PRODUCT_IMAGE;
                    send(chatId, "📸 Send a product photo (or type 'skip' to use placeholder):"); }
                catch (Exception e) { send(chatId, "❌ Invalid category. Try again:"); }
            }
            case PRODUCT_IMAGE -> {
                if (text.equalsIgnoreCase("skip")) {
                    session.productImageUrl = "https://placehold.co/600x400/1a1a2e/ffffff?text=" + session.productName.replace(" ", "+");
                    saveProduct(chatId, session);
                } else send(chatId, "Please send a photo or type 'skip'");
            }
            default -> send(chatId, "❓ Use /help to see available commands.");
        }
    }

    private void handlePhoto(long chatId, Message msg, BotSession session) {
        if (session.state != BotState.PRODUCT_IMAGE) { send(chatId, "Use /addproduct to start adding a product first."); return; }
        try {
            List<PhotoSize> photos = msg.getPhoto();
            PhotoSize largest = photos.stream().max(Comparator.comparingInt(PhotoSize::getFileSize)).orElse(photos.get(photos.size()-1));
            GetFile getFile = new GetFile(largest.getFileId());
            File file = execute(getFile);
            String fileUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
            byte[] imageBytes;
            try (InputStream in = new URL(fileUrl).openStream()) { imageBytes = in.readAllBytes(); }
            session.productImageUrl = imageService.uploadImageFromBytes(imageBytes, "jpg");
            send(chatId, "✅ Photo received! Saving product...");
            saveProduct(chatId, session);
        } catch (Exception e) {
            log.error("Error handling photo: {}", e.getMessage());
            send(chatId, "❌ Failed to process image. Product saved with placeholder image.");
            session.productImageUrl = "https://placehold.co/600x400/1a1a2e/ffffff?text=" + session.productName.replace(" ", "+");
            saveProduct(chatId, session);
        }
    }

    private void handleCommand(long chatId, String text, BotSession session) {
        String cmd = text.split(" ")[0].toLowerCase();
        switch (cmd) {
            case "/start" -> sendWelcome(chatId, session);
            case "/register" -> startRegistration(chatId, session);
            case "/addproduct" -> startAddProduct(chatId, session);
            case "/myproducts" -> showMyProducts(chatId, session);
            case "/profile" -> showProfile(chatId, session);
            case "/help" -> sendHelp(chatId);
            case "/cancel" -> { session.reset(); send(chatId, "❌ Operation cancelled."); }
            default -> send(chatId, "❓ Unknown command. Use /help.");
        }
    }

    private void sendWelcome(long chatId, BotSession session) {
        Optional<User> user = userRepository.findByTelegramChatId(chatId);
        if (user.isPresent()) {
            send(chatId, "👋 Welcome back, *" + user.get().getFullname() + "*!\n\nUse /addproduct to list a new product or /help for all commands.");
        } else {
            send(chatId, "🛒 *Welcome to LocalMarket Bot!*\n\nI help sellers manage their products.\n\n" +
                    "To get started:\n• /register — Create a seller account\n• /help — See all commands\n\n" +
                    "Already have an account? Use /profile to link it.");
        }
    }

    private void startRegistration(long chatId, BotSession session) {
        if (userRepository.findByTelegramChatId(chatId).isPresent()) {
            send(chatId, "✅ You're already registered! Use /profile to view your account."); return;
        }
        session.reset();
        session.state = BotState.WAITING_NAME;
        send(chatId, "📝 *Seller Registration*\n\nLet's set up your account!\n\nEnter your full name:");
    }

    private void completeRegistration(long chatId, BotSession session) {
        try {
            com.example.localmarket.dto.UserDTO dto = new com.example.localmarket.dto.UserDTO();
            dto.setFullname(session.regName);
            dto.setPhoneNumber(session.regPhone);
            dto.setPassword(session.regPassword);
            dto.setProvince(session.regProvince);
            dto.setRole(Role.ROLE_SELLER);
            dto.setShopName(session.shopName);

            User saved = userRepository.findByPhoneNumber(session.regPhone).orElseGet(() -> {
                userService.register(dto);
                return userRepository.findByPhoneNumber(session.regPhone).orElseThrow();
            });
            saved.setTelegramChatId(chatId);
            saved.setTelegramRegistered(true);
            userRepository.save(saved);
            session.reset();
            send(chatId, "🎉 *Registration successful!*\n\nWelcome, *" + session.regName + "*!\n\n" +
                    "Your shop *" + session.shopName + "* is ready.\n\n" +
                    "🌐 Visit: " + appBaseUrl + "\n\n" +
                    "Use /addproduct to list your first product!");
        } catch (Exception e) {
            send(chatId, "❌ Registration failed: " + e.getMessage() + "\n\nTry /register again.");
            session.reset();
        }
    }

    private void startAddProduct(long chatId, BotSession session) {
        Optional<User> userOpt = userRepository.findByTelegramChatId(chatId);
        if (userOpt.isEmpty()) { send(chatId, "❌ Please /register first."); return; }
        session.reset();
        session.state = BotState.PRODUCT_NAME;
        session.sellerChatId = chatId;
        send(chatId, "➕ *Add New Product*\n\nEnter product name:");
    }

    private void saveProduct(long chatId, BotSession session) {
        try {
            User seller = userRepository.findByTelegramChatId(chatId)
                    .orElseThrow(() -> new RuntimeException("Seller not found"));
            ProductDTO dto = new ProductDTO();
            dto.setName(session.productName);
            dto.setDescription(session.productDesc);
            dto.setPrice(session.productPrice);
            dto.setStockQuantity(session.productStock);
            dto.setCategory(session.productCategory);
            dto.setImageUrls(List.of(session.productImageUrl));
            ProductDTO saved = productService.createProduct(dto, seller);
            session.reset();
            send(chatId, "✅ *Product Listed Successfully!*\n\n" +
                    "📦 *" + saved.getName() + "*\n" +
                    "💰 $" + String.format("%.2f", saved.getPrice()) + "\n" +
                    "📊 Stock: " + saved.getStockQuantity() + "\n" +
                    "🏷️ " + saved.getCategory() + "\n\n" +
                    "🌐 View at: " + appBaseUrl + "/product/" + saved.getId());
        } catch (Exception e) {
            send(chatId, "❌ Failed to save product: " + e.getMessage());
            session.reset();
        }
    }

    private void showMyProducts(long chatId, BotSession session) {
        Optional<User> userOpt = userRepository.findByTelegramChatId(chatId);
        if (userOpt.isEmpty()) { send(chatId, "❌ Please /register first."); return; }
        List<ProductDTO> products = productService.getSellerProducts(userOpt.get());
        if (products.isEmpty()) { send(chatId, "📭 No products yet. Use /addproduct to add one!"); return; }
        StringBuilder sb = new StringBuilder("🛍️ *Your Products (" + products.size() + ")*\n\n");
        products.forEach(p -> sb.append("• *").append(p.getName()).append("* — $").append(String.format("%.2f", p.getPrice()))
                .append(" (Stock: ").append(p.getStockQuantity()).append(")\n"));
        sb.append("\n🌐 Manage at: ").append(appBaseUrl).append("/seller/dashboard");
        send(chatId, sb.toString());
    }

    private void showProfile(long chatId, BotSession session) {
        Optional<User> userOpt = userRepository.findByTelegramChatId(chatId);
        if (userOpt.isEmpty()) { send(chatId, "❌ Not registered yet. Use /register."); return; }
        User u = userOpt.get();
        send(chatId, "👤 *Your Profile*\n\n" +
                "Name: " + u.getFullname() + "\n" +
                "Shop: " + (u.getShopName() != null ? u.getShopName() : "—") + "\n" +
                "Phone: " + u.getPhoneNumber() + "\n" +
                "Province: " + u.getProvince() + "\n" +
                "Role: " + u.getRole() + "\n\n" +
                "🌐 " + appBaseUrl + "/seller/dashboard");
    }

    private void sendHelp(long chatId) {
        send(chatId, "📖 *LocalMarket Bot Commands*\n\n" +
                "/start — Welcome message\n" +
                "/register — Create seller account\n" +
                "/addproduct — List a new product\n" +
                "/myproducts — View your products\n" +
                "/profile — View your profile\n" +
                "/cancel — Cancel current operation\n" +
                "/help — Show this help\n\n" +
                "🌐 Website: " + appBaseUrl);
    }

    private void send(long chatId, String text) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(text);
        msg.setParseMode("Markdown");
        try { execute(msg); } catch (TelegramApiException e) { log.error("Failed to send message: {}", e.getMessage()); }
    }

    // --- Inner classes ---
    enum BotState {
        IDLE, WAITING_NAME, WAITING_PHONE, WAITING_PASSWORD, WAITING_PROVINCE, WAITING_SHOP_NAME,
        REGISTERED, PRODUCT_NAME, PRODUCT_DESC, PRODUCT_PRICE, PRODUCT_STOCK, PRODUCT_CATEGORY, PRODUCT_IMAGE
    }

    static class BotSession {
        BotState state = BotState.IDLE;
        String regName, regPhone, regPassword, shopName, productName, productDesc, productImageUrl;
        Province regProvince;
        Double productPrice;
        Integer productStock;
        Category productCategory;
        Long sellerChatId;

        void reset() {
            state = BotState.IDLE;
            regName = regPhone = regPassword = shopName = productName = productDesc = productImageUrl = null;
            regProvince = null; productPrice = null; productStock = null; productCategory = null; sellerChatId = null;
        }
    }
}
