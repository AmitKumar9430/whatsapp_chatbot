package com.chatbot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OutgoingMessage {

    private String to;
    private String reply;
    private String timestamp;
    private String status;

    // Constructors
    public OutgoingMessage() {}

    public OutgoingMessage(String to, String reply) {
        this.to = to;
        this.reply = reply;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.status = "sent";
    }

    // Getters and Setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OutgoingMessage{to='" + to + "', reply='" + reply + "', timestamp='" + timestamp + "'}";
    }
}
