# ---------- Build stage ----------
FROM gradle:9.3.1-jdk21 AS builder
WORKDIR /app

# Copy only needed files
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle.kts ./
COPY src/ src/

# Make gradlew executable
RUN chmod +x gradlew

# Build Spring Boot jar using wrapper
RUN ./gradlew clean bootJar --no-daemon --stacktrace

# ---------- Run stage ----------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy jar from builder
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 5477

ENTRYPOINT ["java","-jar","/app/app.jar"]