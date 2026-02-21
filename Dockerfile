# ---------- Build stage ----------
FROM gradle:8.7-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

# ---------- Run stage ----------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# copy jar from builder
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 5477

ENTRYPOINT ["java","-jar","/app/app.jar"]