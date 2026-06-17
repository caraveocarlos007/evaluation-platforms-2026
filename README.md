# Evaluation Platforms Especiales 2026

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-green)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)
![Frontend](https://img.shields.io/badge/React-Nginx-61dafb)
![Database](https://img.shields.io/badge/H2-InMemory-orange)

---

## Descripción

Sistema distribuido basado en **microservicios** para procesamiento de transacciones, con autenticación, validación, persistencia y frontend integrado.

Incluye:

- Login con Spring Security + BCrypt
- Procesamiento de transacciones con validación DTO
- Comunicación entre APIs vía REST
- Persistencia con JPA + H2
- Frontend React + Nginx
- Orquestación con Docker Compose

---

## Arquitectura

- Frontend: React + Nginx  
- Gateway API: Spring Boot (orquestador)  
- Transaction API: Spring Boot + JPA + H2  
- Comunicación: REST  
- Seguridad: Spring Security + BCrypt + CORS  
- Contenedores: Docker + Docker Compose  

---

## Screenshots

### Login
<img width="1886" height="1062" alt="image" src="https://github.com/user-attachments/assets/a56625d7-8199-4a93-a5ae-909338afb1f4" />


### Registro de transacción
<img width="1885" height="1056" alt="image" src="https://github.com/user-attachments/assets/8bc0d545-421c-4fbe-8bb7-a746bb0ae8df" />


---

## Swagger

- Gateway API: http://localhost:8082/swagger-ui/index.html  
- Transaction API: http://localhost:8081/swagger-ui/index.html  

---

## Requisitos

- Docker  
- Docker Compose  
- Java 21  
- Maven  
- Node.js

---

## Compilación previa (OBLIGATORIO)

Antes de levantar Docker, genera los JARs:

### Transaction API
```bash
cd transaction-api
mvn clean package -DskipTests
```

---

### Gateway API
```bash
cd transaction-gateway-api
mvn clean package -DskipTests
```

---

### Frontend
```bash
cd frontend-react
npm install
npm run build
```

---

## Levantar proyecto

Desde la raíz del proyecto:

```bash
docker compose up --build
```

---

## Accesos
Servicio	URL
Frontend	http://localhost:3000
Gateway API	http://localhost:8082
Transaction API	http://localhost:8081
Swagger Gateway	http://localhost:8082/swagger-ui/index.html
Swagger Transaction	http://localhost:8081/swagger-ui/index.html

---

## Credenciales de prueba
Usuario: admin
Password: 1234

---

## Autor
Ruben Caraveo Garcia

Proyecto desarrollado como evaluación de plataformas 2026
Arquitectura basada en microservicios con Spring Boot + React + Docker
