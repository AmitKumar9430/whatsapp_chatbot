# WhatsApp Chatbot Backend 

A simple WhatsApp chatbot backend built using Java and Spring Boot. It exposes a REST API that accepts incoming messages and returns automated replies — simulating how a real WhatsApp webhook works behind the scenes.

---

##  Live Demo

The project is deployed and live on Render. You can test it right now:

**Base URL:**
```
https://whatsapp-chatbot-1-ibjq.onrender.com
```

> First request may take 30-50 seconds to respond because the free instance sleeps after inactivity. After the first request it will be fast.

---

## Test the Live API

### 1. Send a Hi message
```
POST https://whatsapp-chatbot-1-ibjq.onrender.com/webhook
```
Body:
```json
{
  "from": "919876543210",
  "message": "Hi"
}
```
Expected response:
```json
{
  "to": "919876543210",
  "reply": "Hello! How can I assist you today?",
  "timestamp": "2026-04-06 10:39:21",
  "status": "sent"
}
```

---

### 2. Send a Bye message
```
POST https://whatsapp-chatbot-1-ibjq.onrender.com/webhook
```
Body:
```json
{
  "from": "919876543210",
  "message": "Bye"
}
```
Expected response:
```json
{
  "to": "919876543210",
  "reply": "Goodbye! Have a wonderful day!",
  "timestamp": "2026-04-06 10:39:57",
  "status": "sent"
}
```

---

### 3. View all conversation logs
```
GET https://whatsapp-chatbot-1-ibjq.onrender.com/webhook/logs
```

---

### 4. Health check
```
GET https://whatsapp-chatbot-1-ibjq.onrender.com/webhook/health
```
Expected response:
```json
{
  "service": "WhatsApp Chatbot Backend",
  "version": "1.0.0",
  "status": "UP"
}
```

---

### 5. Full system health
```
GET https://whatsapp-chatbot-1-ibjq.onrender.com/actuator/health
```

---

## Why I built this

This was built as part of an internship assignment. The goal was to create a REST API that behaves like a WhatsApp webhook — accepting messages and sending back smart replies automatically.

The focus was on:
- Clean, readable code
- Proper REST API design
- Logging every conversation
- Simple but extensible reply logic

---

## What it does

- Accepts incoming messages via a REST API
- Sends automatic replies based on keywords
- Logs every message with a timestamp and unique ID
- Lets you retrieve full conversation history
- Has health check endpoints for monitoring
- Handles messages case-insensitively
- Returns a friendly fallback for anything it doesn't understand

---

## Tech stack

- Java 17
- Spring Boot 3.2
- Maven
- Docker (for deployment)

---

## Running it locally

Make sure Java 17+ and Maven are installed, then:

```bash
git clone https://github.com/AmitKumar9430/whatsapp_chatbot.git
cd whatsapp_chatbot
mvn spring-boot:run
```

Server starts at `http://localhost:8080`

---

## Supported messages

| You send | Bot replies |
|----------|-------------|
| Hi / Hello / Hey | Hello! How can I assist you today? |
| Bye / Goodbye | Goodbye! Have a wonderful day! |
| How are you | I'm doing great, thanks for asking! |
| Help | Lists all available commands |
| About | Information about the bot |
| Hours / Timing | Business hours information |
| Thank you | You're welcome! |
| Anything else | Friendly fallback message |

---

## Screenshots

### POST /webhook → Hi
<img width="1919" height="1014" alt="Hi message test" src="https://github.com/user-attachments/assets/248d3f8d-e5c4-4530-803a-a95b91fc7e76" />

### POST /webhook → Bye
<img width="1919" height="1014" alt="Bye message test" src="https://github.com/user-attachments/assets/bc6bf763-07d9-4884-8153-58050a1cb7a4" />

### GET /webhook/logs
<img width="1919" height="1014" alt="Webhook logs" src="https://github.com/user-attachments/assets/616050c1-3a28-4beb-bc4c-ee17a711d0c3" />

### GET /actuator/health
<img width="1919" height="1019" alt="Actuator health" src="https://github.com/user-attachments/assets/4a23a8f1-1e78-4f04-a0d9-88a578db5cf1" />

---

## Project structure

```
src/
├── controller/
│   └── WebhookController.java   ← all API endpoints
├── service/
│   └── ChatbotService.java      ← reply logic + logging
├── model/
│   ├── IncomingMessage.java     ← incoming request model
│   ├── OutgoingMessage.java     ← outgoing response model
│   └── MessageLog.java          ← conversation log model
└── WhatsAppChatbotApplication.java  ← entry point
```

---

## Running the tests

```bash
mvn test
```

8 unit tests covering reply logic, case-insensitivity, logging, and fallback responses.

---

## Deployment

Deployed on [Render](https://render.com) using Docker.

Live at: **https://whatsapp-chatbot-1-ibjq.onrender.com**

---

— Amit Kumar
