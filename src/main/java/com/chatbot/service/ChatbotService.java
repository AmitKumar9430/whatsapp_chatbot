package com.chatbot.service;

import com.chatbot.model.IncomingMessage;
import com.chatbot.model.MessageLog;
import com.chatbot.model.OutgoingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);

    // In-memory log store (thread-safe)
    private final List<MessageLog> messageLogs = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger messageCount = new AtomicInteger(0);

    public OutgoingMessage processMessage(IncomingMessage incoming) {
        String senderNumber = incoming.getFrom();
        String userMessage  = incoming.getMessage().trim();

        logger.info("Incoming message from [{}]: {}", senderNumber, userMessage);

        String replyText = generateReply(userMessage);

        String logId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        MessageLog log = new MessageLog(logId, senderNumber, userMessage, replyText);
        messageLogs.add(log);
        messageCount.incrementAndGet();

        logger.info(" Reply to [{}]: {} | Log ID: {}", senderNumber, replyText, logId);

        return new OutgoingMessage(senderNumber, replyText);
    }

    private String generateReply(String message) {
        String normalized = message.toLowerCase().replaceAll("[^a-z0-9 ]", "").trim();

        return switch (normalized) {
            case "hi", "hello", "hey", "hii", "helo" ->
                    "Hello!  How can I assist you today?";

            case "bye", "goodbye", "see you", "see ya", "cya", "tata" ->
                    "Goodbye!  Have a wonderful day!";

            case "how are you", "how r u", "how are u", "wassup", "whats up" ->
                    "I'm doing great, thanks for asking!  How can I help?";

            case "help", "support", "assist me" ->
                    "Sure! You can ask me anything. Try: Hi, Bye, Help, About, or Hours.";

            case "about", "who are you", "what are you" ->
                    "I'm your WhatsApp Assistant Bot built with Spring Boot. Here to help 24/7!";

            case "hours", "working hours", "timing", "timings" ->
                    "We're available 24/7! Our team responds during business hours (9 AM – 6 PM IST).";

            case "thank you", "thanks", "thankyou", "thx", "ty" ->
                    "You're welcome! Feel free to ask if you need anything else.";

            case "ok", "okay", "alright", "sure", "got it" ->
                    "Great!  Let me know if there's anything else I can help with.";

            default ->
                    "I didn't quite understand that. Try: Hi, Bye, Help, About, or Hours.";
        };
    }

    public List<MessageLog> getAllLogs() {
        return Collections.unmodifiableList(messageLogs);
    }

    public int getMessageCount() {
        return messageCount.get();
    }

    public void clearLogs() {
        messageLogs.clear();
        logger.warn(" All message logs have been cleared.");
    }
}
