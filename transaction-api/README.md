# Transaction API

Technical Assessment 2026

## Overview

Transaction API is a Spring Boot microservice responsible for managing financial transactions in a distributed system architecture using an API Gateway and JWT-based authentication.

It provides functionality for creating, listing, and canceling transactions, and it is consumed through the Gateway API.

## Technologies

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Web
- Maven
- JWT (handled via Gateway)
- Docker
- OpenAPI / Swagger
- JUnit 5

## Architecture

The service follows a layered architecture:

src/main/java
├── application
│   ├── dto
│   ├── service
│
├── domain
│   ├── model
│   ├── repository
│
├── infrastructure
│   ├── controller
│   ├── persistence
│   ├── config

## Features

### Create Transaction
Creates a new transaction in the system.

### List Transactions (Paginated)
Returns paginated results.

Example:
GET /api/gateway/transactions?page=0&size=10&sortBy=id

### Cancel Transaction
Performs a logical cancellation of a transaction.

PATCH /api/gateway/transactions/status

Request:
{
  "id": 1,
  "referencia": "608284",
  "estatus": "cancelado"
}

### Status Rules
- Aprobada
- Cancelado

Cancelled transactions remain in history but are not actionable.

## Running Locally

### Build
mvn clean package -DskipTests

### Run
mvn spring-boot:run

Service runs at:
http://localhost:8081

## Docker

### Build Image
docker build -t transaction-api .

### Run Container
docker run -p 8081:8081 transaction-api

## Gateway Integration

All endpoints are accessed through:
http://localhost:8082/api/gateway

Authentication is required via JWT Bearer token.

## Example Requests

### Create Transaction
POST /transactions

{
  "operacion": "venta",
  "importe": 100,
  "cliente": "Carlos",
  "secreto": "1234"
}

### List Transactions
GET /transactions?page=0&size=10&sortBy=id

### Cancel Transaction
PATCH /transactions/status

{
  "id": 1,
  "referencia": "608284",
  "estatus": "cancelado"
}

## Testing

mvn test

## Author

Carlos Caraveo  
Technical Assessment 2026