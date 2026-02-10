# Use lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the jar
RUN ./mvnw clean package -DskipTests

# Run the jar
CMD ["java", "-jar", "target/bfhl-0.0.1-SNAPSHOT.jar"]