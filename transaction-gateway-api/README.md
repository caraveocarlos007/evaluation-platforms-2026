# Gateway API

Technical Assessment 2026

## Overview

Gateway API developed using Spring Boot and JWT-based security.

This service is responsible for:

- Authenticating users.
- Generating and validating JWT tokens.
- Protecting secured endpoints.
- Routing requests to downstream services.
- Providing API documentation via Swagger/OpenAPI.
- Running containerized with Docker.

---

## Technologies

- Java 21
- Spring Boot 4
- Spring Security
- JWT (JSON Web Token)
- Spring Web
- Maven
- Docker
- Swagger OpenAPI
- JUnit 5

---

## Architecture

The project follows a layered architecture focused on security and API gateway responsibilities.

src/main/java
|
├── application
│   └── dto
│
├── infrastructure
│   ├── security
│   │   ├── AuthController
│   │   ├── JwtService
│   │   ├── JwtAuthenticationFilter
│   │   └── SecurityConfig
│   │
│   └── config
│       └── OpenApiConfig

---

## Features

### Authentication

Login endpoint that validates credentials and generates a JWT token.

### JWT Security

- Token generation
- Token validation
- Request filtering using Spring Security filter chain

### Protected Endpoints

Endpoints secured using Bearer Token authentication.

### Swagger Documentation

Interactive API documentation with JWT support.

### Unit Tests

JUnit tests for:

- AuthController
- JwtService

---

## Running Locally

### Build

mvn clean package -DskipTests

### Run

mvn spring-boot:run

Application available at:

http://localhost:8082

---

## Docker

### Build Image

mvn clean package -DskipTests  
docker build -t gateway-api .

### Run Container

docker run -p 8082:8082 gateway-api

If port is in use:

docker run -p 8083:8082 gateway-api

---

## Swagger

http://localhost:8082/swagger-ui/index.html

### Authentication in Swagger

Click Authorize and use:

Bearer <your-jwt-token>

---

## Endpoints

### Login

POST /api/auth/login

Request:

{
  "username": "admin",
  "password": "admin123"
}

Response:

{
  "token": "jwt-token"
}

---

### Secured Test Endpoint

GET /api/gateway/secure

Header:

Authorization: Bearer <token>

---

## Security Flow

1. Client sends credentials to login endpoint.
2. Gateway validates credentials.
3. JWT token is generated and returned.
4. Client sends token in Authorization header.
5. JwtAuthenticationFilter validates token on each request.
6. Request is allowed or rejected based on validation.

---

## Testing

mvn test

Includes:

- AuthControllerTest
- JwtServiceTest

---

## Author

Carlos Caraveo

Technical Assessment 2026