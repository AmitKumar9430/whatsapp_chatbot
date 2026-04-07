package com.chatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> home() {
        String html = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>WhatsApp Chatbot</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                        background-color: #075E54;
                        color: white;
                        text-align: center;
                    }
                    .card {
                        background-color: #128C7E;
                        padding: 40px 60px;
                        border-radius: 16px;
                        box-shadow: 0 4px 20px rgba(0,0,0,0.3);
                    }
                    h1 { font-size: 2rem; margin-bottom: 10px; }
                    p  { font-size: 1rem; opacity: 0.9; margin: 6px 0; }
                    .badge {
                        display: inline-block;
                        background: #25D366;
                        padding: 6px 16px;
                        border-radius: 20px;
                        margin-top: 16px;
                        font-size: 0.85rem;
                        font-weight: bold;
                    }
                    .endpoints {
                        margin-top: 24px;
                        text-align: left;
                        background: rgba(0,0,0,0.2);
                        padding: 16px 24px;
                        border-radius: 10px;
                        font-size: 0.85rem;
                        line-height: 2;
                    }
                </style>
            </head>
            <body>
                <div class="card">
                    <h1>Welcome to WhatsApp Chatbot Backend</h1>
                    <p>Built with Java & Spring Boot</p>
                    <p>Deployed on Render</p>
                    <div class="badge"> Server is Live</div>
                    <div class="endpoints">
                        <strong>Available Endpoints:</strong><br>
                        POST &nbsp;/webhook &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Send a message<br>
                        GET &nbsp;&nbsp;/webhook/logs &nbsp;→ View all logs<br>
                        GET &nbsp;&nbsp;/webhook/health → Health check<br>
                        GET &nbsp;&nbsp;/actuator/health → System health
                    </div>
                </div>
            </body>
            </html>
            """;
        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(html);
    }
}