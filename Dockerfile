FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build jar
RUN ./mvnw clean package -DskipTests

# Run app
CMD ["java", "-jar", "target/bfhl-0.0.1-SNAPSHOT.jar"]