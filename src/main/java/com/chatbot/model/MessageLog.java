package com.chatbot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageLog {

    private String id;
    private String from;
    private String incomingMessage;
    private String botReply;
    private String timestamp;

    public MessageLog(String id, String from, String incomingMessage, String botReply) {
        this.id = id;
        this.from = from;
        this.incomingMessage = incomingMessage;
        this.botReply = botReply;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getters
    public String getId() { return id; }
    public String getFrom() { return from; }
    public String getIncomingMessage() { return incomingMessage; }
    public String getBotReply() { return botReply; }
    public String getTimestamp() { return timestamp; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setFrom(String from) { this.from = from; }
    public void setIncomingMessage(String incomingMessage) { this.incomingMessage = incomingMessage; }
    public void setBotReply(String botReply) { this.botReply = botReply; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] FROM: %s | MSG: %s | REPLY: %s", timestamp, from, incomingMessage, botReply);
    }
}
