# ---------- Build stage ----------
FROM gradle:9.3.1-jdk21 AS builder
WORKDIR /app

# Copy wrapper and Gradle files
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle.kts settings.gradle.kts ./
COPY src/ src/

# Make wrapper executable
RUN chmod +x gradlew

# Build Spring Boot jar
RUN ./gradlew clean bootJar --no-daemon --stacktrace --refresh-dependencies

# ---------- Run stage ----------
FROM eclipse-temurin:20-jre-jammy
WORKDIR /app

# Copy jar from builder
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 5477

ENTRYPOINT ["java","-jar","app.jar"]