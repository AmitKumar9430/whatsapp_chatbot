WhatsApp Chatbot Backend

This is a simple WhatsApp chatbot backend built using Java and Spring Boot.
It exposes a REST API that accepts incoming messages and returns automated replies.
It simulates how a basic WhatsApp webhook works behind the scenes.

Why I built this

I built this project as part of an internship assignment.
The objective was to create a REST API that behaves like a WhatsApp webhook — accepting messages and sending back responses automatically.

The focus was on:

Clean code
Proper API design
Logging conversations
Simple reply logic
Features
Accepts incoming messages via REST API
Sends automatic replies based on keywords
Logs every message with timestamp and ID
Retrieve conversation history
Health check endpoints
Case-insensitive message handling
Fallback reply for unknown messages
Tech Stack
Java 17
Spring Boot 3.2
Maven
REST API
Run Locally

Make sure Java 17+ and Maven are installed.

Clone the repository:

git clone https://github.com/AmitKumar9430/whatsapp-chatbot.git
cd whatsapp-chatbot
mvn spring-boot:run

The server will start at:

http://localhost:8080
Send Your First Message

POST request:

POST http://localhost:8080/webhook

Request body:

{
  "from": "919876543210",
  "message": "Hi"
}

Response:

{
  "to": "919876543210",
  "reply": "Hello! How can I assist you today?",
  "timestamp": "2026-04-06 10:39:21",
  "status": "sent"
}
Supported Messages
Message	Reply
Hi / Hello / Hey	Hello! How can I assist you today?
Bye / Goodbye	Goodbye! Have a wonderful day!
How are you	I'm doing great, thanks for asking!
Help	Lists all available commands
About	Information about the bot
Hours / Timing	Business hours information
Thank you	You're welcome!
Other	Friendly fallback message
API Endpoints

Send message

POST /webhook

 <img width="1919" height="1014" alt="Screenshot 2026-04-06 103944" src="https://github.com/user-attachments/assets/248d3f8d-e5c4-4530-803a-a95b91fc7e76" />


Get conversation logs

GET /webhook/logs


<img width="1919" height="1014" alt="Screenshot 2026-04-06 103944" src="https://github.com/user-attachments/assets/bc6bf763-07d9-4884-8153-58050a1cb7a4" />


Health check

GET /webhook/health

<img width="1919" height="1014" alt="Screenshot 2026-04-06 103944" src="https://github.com/user-attachments/assets/616050c1-3a28-4beb-bc4c-ee17a711d0c3" />


Application health

GET /actuator/health

<img width="1919" height="1019" alt="image" src="https://github.com/user-attachments/assets/4a23a8f1-1e78-4f04-a0d9-88a578db5cf1" />


Project Structure


 <img width="608" height="685" alt="image" src="https://github.com/user-attachments/assets/edc281cb-a360-4e4c-afd6-a5486d81a49c" />

Running Tests

The project includes unit tests for reply logic, logging, and fallback responses.

Run tests:
mvn spring-boot:run   
Deployment

The project includes a render.yaml file for easy deployment on Render.

Steps:

Push repository to GitHub
Connect GitHub to Render
Deploy using render.yaml
