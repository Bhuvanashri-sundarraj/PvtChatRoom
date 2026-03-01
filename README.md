# 💬 PvtChatRoom
PvtChatRoom is a secure, room-based real-time chat application built using **Spring Boot**, **WebSocket (STOMP)**, **Thymeleaf**, and **Google OAuth2 authentication**.
It allows users to create private chat rooms protected by a password and communicate in real-time using WebSockets.

<hr>

## 🚀 Features

- 🔐 Google OAuth2 Login
- 🏠 Room-based chat system
- 🔑 First user sets room password
- 👥 Other users must enter correct password to join
- ⚡ Real-time messaging using WebSocket (STOMP + SockJS)
- 🕒 Join and leave notifications with timestamps
- 🎨 Clean and responsive UI (Bootstrap based)
- ☁️ Environment variable-based secret management

<hr>

## 🛠️ Tech Stack

**Backend**
- Java 17+
- Spring Boot
- Spring Security (OAuth2)
- Spring WebSocket
- STOMP Protocol
- Embedded Tomcat

**Frontend**
- Thymeleaf
- Bootstrap 5
- JavaScript
- SockJS
- STOMP.js

<hr>

## 🧠 Architecture Overview


Client (Browser / Phone)
↓
WebSocket (STOMP over SockJS)
↓
Spring Boot WebSocket Broker
↓
In-memory Room Management (HashMap)


- Messages are broadcast to `/topic/messages`
- Room authentication handled in memory
- OAuth2 handles secure login
- Environment variables protect sensitive credentials

<hr>

## 🔐 Room Authentication Logic

- If a room does not exist → First user creates it with a password.
- If room exists → Password must match.
- Incorrect password → Join is denied.
- Room data is stored in-memory (for demo purpose).

<hr>

## ⚙️ Installation & Running Locally

### 1️⃣ Clone the repository
- git clone https://github.com/Bhuvanashri-sundarraj/PvtChatRoom.git
- cd PvtChatRoom
### 2️⃣ Set Environment Variables
- Create environment variables for OAuth:
<br>GOOGLE_CLIENT_ID=your_client_id
<br>GOOGLE_CLIENT_SECRET=your_client_secret
### 3️⃣ Run the Application
- Using Maven:
<br>mvn spring-boot:run
- Or run AppApplication.java from IntelliJ.
### 4️⃣ Open in Browser
- http://localhost:8080

### 🌍 Demo Using ngrok (Optional if not ready for deployment in cloud)
- To expose your local app publicly:
<br>ngrok http 8080
- This generates a public URL like:
<br>https://abc123.ngrok-free.app
- Use this link on multiple devices to demonstrate real-time chat.
<br>
⚠️ If using OAuth, update Google Console with:<br>
- Authorized JavaScript Origin
<br>https://abc123.ngrok-free.app<br>
- Authorized Redirect URI
<br>https://abc123.ngrok-free.app/login/oauth2/code/google
<br>🔑 OAuth Configuration<br>
- In application.properties:
<br>spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
<br>spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}
<br>spring.security.oauth2.client.registration.google.scope=openid,profile,email
<br>Secrets are injected via environment variables.

## 📂 Project Structure
src/
<br> ├── main/
<br>│   ├── java/com/chat/app/<br>
 │   │   ├── config/<br>
 │   │   │   └── WebSocketConfig.java<br>
 │   │   ├── controller/<br>
 │   │   │   └── ChatController.java<br>
 │   │   ├── model/<br>
 │   │   │   └── ChatMessage.java<br>
 │   │   └── AppApplication.java<br>
 │   └── resources/<br>
 │       ├── templates/<br>
 │       │   └── chat.html<br>
 │       └── application.properties<br>

## 📌 Future Improvements
- Persistent room storage (Redis / Database)
- User presence tracking
- Private messaging
- Message history persistence
- Production-grade authentication & authorization
- Deployment with Docker + Cloud provider

## 🧪 Testing the Application
- Open application in two browsers (or devices).
- Join the same room with correct password.
- Start chatting in real-time.
- Test incorrect password behavior.

## 📄 License
This project is built for educational and demonstration purposes.

## 👩‍💻 Author
Bhuvanashri Sundarraj
