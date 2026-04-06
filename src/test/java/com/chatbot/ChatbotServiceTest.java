package com.chatbot;

import com.chatbot.model.IncomingMessage;
import com.chatbot.model.OutgoingMessage;
import com.chatbot.service.ChatbotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatbotServiceTest {

    private ChatbotService chatbotService;

    @BeforeEach
    void setUp() {
        chatbotService = new ChatbotService();
    }

    @Test
    @DisplayName("Hi should return Hello greeting")
    void testHiReply() {
        IncomingMessage msg = new IncomingMessage("9199999999", "Hi");
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertTrue(reply.getReply().contains("Hello"));
    }

    @Test
    @DisplayName("Bye should return Goodbye message")
    void testByeReply() {
        IncomingMessage msg = new IncomingMessage("9199999999", "Bye");
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertTrue(reply.getReply().contains("Goodbye"));
    }

    @Test
    @DisplayName("Unknown message should return default fallback")
    void testUnknownReply() {
        IncomingMessage msg = new IncomingMessage("9199999999", "random xyz");
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertNotNull(reply.getReply());
        assertFalse(reply.getReply().isEmpty());
    }

    @Test
    @DisplayName("Messages should be logged")
    void testMessageLogging() {
        chatbotService.processMessage(new IncomingMessage("9199999999", "Hi"));
        chatbotService.processMessage(new IncomingMessage("9199999999", "Bye"));
        assertEquals(2, chatbotService.getMessageCount());
    }

    @Test
    @DisplayName("Reply should have correct recipient")
    void testReplyRecipient() {
        String number = "9187654321";
        IncomingMessage msg = new IncomingMessage(number, "Hello");
        OutgoingMessage reply = chatbotService.processMessage(msg);
        assertEquals(number, reply.getTo());
    }

    @Test
    @DisplayName("Reply status should be 'sent'")
    void testReplyStatus() {
        OutgoingMessage reply = chatbotService.processMessage(new IncomingMessage("91999", "Hi"));
        assertEquals("sent", reply.getStatus());
    }

    @Test
    @DisplayName("Case insensitive: HI should return Hello")
    void testCaseInsensitivity() {
        OutgoingMessage reply = chatbotService.processMessage(new IncomingMessage("91999", "HI"));
        assertTrue(reply.getReply().contains("Hello"));
    }

    @Test
    @DisplayName("Clear logs should reset count")
    void testClearLogs() {
        chatbotService.processMessage(new IncomingMessage("91999", "Hi"));
        chatbotService.clearLogs();
        assertEquals(0, chatbotService.getMessageCount());
    }
}
