package com.chat.app.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.chat.app.model.ChatMessage;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatController {

    private final Map<String, String> rooms = new HashMap<>();

    private String getTime() {
        return LocalTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    @MessageMapping("/join")
    @SendTo("/topic/messages")
    public ChatMessage join(ChatMessage message) {

        String room = message.getRoom();
        String password = message.getContent();

        ChatMessage response = new ChatMessage();
        response.setSender(message.getSender());
        response.setRoom(room);
        response.setTime(getTime());

        if (!rooms.containsKey(room)) {
            rooms.put(room, password);
            response.setType(ChatMessage.MessageType.JOIN);
            return response;
        }

        if (!rooms.get(room).equals(password)) {
            response.setType(ChatMessage.MessageType.ERROR);
            response.setContent("Wrong password");
            return response;
        }

        response.setType(ChatMessage.MessageType.JOIN);
        return response;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        message.setType(ChatMessage.MessageType.CHAT);
        message.setTime(getTime());
        return message;
    }

    @MessageMapping("/leave")
    @SendTo("/topic/messages")
    public ChatMessage leave(ChatMessage message) {
        message.setType(ChatMessage.MessageType.LEAVE);
        message.setTime(getTime());
        return message;
    }
    @GetMapping("/")
    public String login() {
        return "login";
    }
    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}