package com.chatbot.model;

import jakarta.validation.constraints.NotBlank;

public class IncomingMessage {

    @NotBlank(message = "Sender phone number is required")
    private String from;

    @NotBlank(message = "Message content is required")
    private String message;

    public IncomingMessage() {}

    public IncomingMessage(String from, String message) {
        this.from = from;
        this.message = message;
    }

    // Getters and Setters
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "IncomingMessage{from='" + from + "', message='" + message + "'}";
    }
}
