package com.example.localmarket.bot;

import com.example.localmarket.dto.UserDTO;
import com.example.localmarket.entity.Province;
import com.example.localmarket.service.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class SellerRegistrationBot extends TelegramLongPollingBot {

    private final UserService userService;

    public SellerRegistrationBot(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getBotUsername() {
        return "localmarket_khBOT";
    }

    @Override
    public String getBotToken() {
        return "8296954710:AAGi2DMq1PlZRTxmTN988O4U-pBlDN0-FGk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            String text = message.getText();

            // For simplicity, assume user sends data as "Name,Phone,Password,Province"
            if (text.contains(",")) {
                String[] parts = text.split(",");
                if (parts.length == 4) {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setFullname(parts[0].trim());
                    userDTO.setPhoneNumber(parts[1].trim());
                    userDTO.setPassword(parts[2].trim());

                    // Convert province string to enum
                    try {
                        userDTO.setProvince(Province.valueOf(parts[3].trim().toUpperCase()));
                        userService.createUser(userDTO);

                        sendMessage(chatId, "✅ Registration successful! Welcome, " + parts[0].trim());
                    } catch (IllegalArgumentException e) {
                        sendMessage(chatId, "❌ Invalid province. Please use a valid one like PHNOM_PENH, SIEM_REAP, TAKEO.");
                    }
                } else {
                    sendMessage(chatId, "❌ Incorrect format. Please send: Name,Phone,Password,Province");
                }
            } else {
                sendMessage(chatId, "❌ Please send your info in format: Name,Phone,Password,Province");
            }
        }
    }

    private void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
