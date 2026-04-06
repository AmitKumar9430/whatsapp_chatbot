package com.chatbot.controller;

import com.chatbot.model.IncomingMessage;
import com.chatbot.model.MessageLog;
import com.chatbot.model.OutgoingMessage;
import com.chatbot.service.ChatbotService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/webhook")
@CrossOrigin(origins = "*")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    private final ChatbotService chatbotService;

    public WebhookController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }
    @PostMapping
    public ResponseEntity<OutgoingMessage> receiveMessage(
            @Valid @RequestBody IncomingMessage incomingMessage) {

        logger.info("📬 POST /webhook received from: {}", incomingMessage.getFrom());

        OutgoingMessage response = chatbotService.processMessage(incomingMessage);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logs")
    public ResponseEntity<Map<String, Object>> getAllLogs() {
        List<MessageLog> logs = chatbotService.getAllLogs();

        Map<String, Object> response = new HashMap<>();
        response.put("totalMessages", chatbotService.getMessageCount());
        response.put("logs", logs);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/logs/count")
    public ResponseEntity<Map<String, Object>> getLogCount() {
        Map<String, Object> response = new HashMap<>();
        response.put("totalMessages", chatbotService.getMessageCount());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "WhatsApp Chatbot Backend");
        response.put("version", "1.0.0");
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/logs")
    public ResponseEntity<Map<String, String>> clearLogs() {
        chatbotService.clearLogs();
        Map<String, String> response = new HashMap<>();
        response.put("message", "All logs cleared successfully.");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
