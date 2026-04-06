# WhatsApp Chatbot Backend 

A simple WhatsApp chatbot backend built using Java and Spring Boot. It exposes a REST API that accepts incoming messages and returns automated replies — simulating how a real WhatsApp webhook works behind the scenes.

---

##  Live Demo

The project is deployed and live on Render. You can test it right now without running anything locally:

**Base URL:**
```
https://whatsapp-chatbot-1-ibjq.onrender.com
```

>  First request may take 30-50 seconds to respond because the free instance sleeps after inactivity. After the first request it will be fast.

---

## 🧪 How to Test the API

### What is Postman?

Postman is a free tool used to test APIs. It lets you send requests to any API endpoint and see the response — without writing any code.

**Download Postman here:**  https://www.postman.com/downloads/

Once installed:
1. Open Postman
2. Click **New → HTTP Request**
3. Choose the method (GET or POST)
4. Paste the URL
5. For POST requests → click **Body → raw → select JSON**
6. Paste the request body
7. Click **Send**

---

## Test the Live API

### Test 1 — Send a Hi message

- **Method:** `POST`
- **URL:** `https://whatsapp-chatbot-1-ibjq.onrender.com/webhook`
- **Body (raw JSON):**
```json
{
  "from": "919876543210",
  "message": "Hi"
}
```
- **Expected response:**
```json
{
  "to": "919876543210",
  "reply": "Hello! How can I assist you today?",
  "timestamp": "2026-04-06 10:39:21",
  "status": "sent"
}
```
 <img width="1919" height="1023" alt="image" src="https://github.com/user-attachments/assets/74df29cd-8a95-4841-b05e-282cb47e3e3b" />

---

### Test 2 — Send a Bye message

- **Method:** `POST`
- **URL:** `https://whatsapp-chatbot-1-ibjq.onrender.com/webhook`
- **Body (raw JSON):**
```json
{
  "from": "919876543210",
  "message": "Bye"
}
```
- **Expected response:**
```json
{
  "to": "919876543210",
  "reply": "Goodbye! Have a wonderful day!",
  "timestamp": "2026-04-06 10:39:57",
  "status": "sent"
}
```

<img width="1916" height="1016" alt="Screenshot 2026-04-06 122739" src="https://github.com/user-attachments/assets/222987c6-ef4e-47db-baea-9d8be7242bc0" />


---

### Test 3 — View all conversation logs

- **Method:** `GET`
- **URL:** `https://whatsapp-chatbot-1-ibjq.onrender.com/webhook/logs`
- No body needed — just hit Send

<img width="1919" height="1014" alt="Webhook logs" src="https://github.com/user-attachments/assets/616050c1-3a28-4beb-bc4c-ee17a711d0c3" />

---

### Test 4 — Health check

- **Method:** `GET`
- **URL:** `https://whatsapp-chatbot-1-ibjq.onrender.com/webhook/health`
- **Expected response:**
```json
{
  "service": "WhatsApp Chatbot Backend",
  "version": "1.0.0",
  "status": "UP"
}
```

---

### Test 5 — Full system health

- **Method:** `GET`
- **URL:** `https://whatsapp-chatbot-1-ibjq.onrender.com/actuator/health`

<img width="1919" height="1019" alt="Actuator health" src="https://github.com/user-attachments/assets/4a23a8f1-1e78-4f04-a0d9-88a578db5cf1" />

---

## 💬 All Supported Messages

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

## Why I built this

This was built as part of an internship assignment. The goal was to create a REST API that behaves like a WhatsApp webhook — accepting messages and sending back smart replies automatically.

The focus was on:
- Clean, readable code
- Proper REST API design
- Logging every conversation
- Simple but extensible reply logic

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

Then test locally in Postman using:
```
POST http://localhost:8080/webhook
GET  http://localhost:8080/webhook/logs
GET  http://localhost:8080/webhook/health
GET  http://localhost:8080/actuator/health
```

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
