FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests 2>/dev/null || mvn clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/whatsapp-chatbot-1.0.0.jar"]