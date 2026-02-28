package com.chat.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessage {

    private String sender;
    private String content;
    private String room;
    private MessageType type;
    private String time;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        ERROR
    }
}