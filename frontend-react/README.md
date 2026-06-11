# Frontend - Transaction Platform

Technical Assessment 2026

## Overview

Frontend application built with React + Vite for a transaction management system.

This UI connects to a Spring Boot API Gateway and allows users to:

- Login with JWT authentication
- Create transactions
- View paginated transactions
- Cancel transactions
- Navigate through a dashboard interface

All requests are authenticated using a Bearer token stored in localStorage.

---

## Technologies

- React 18
- Vite
- React Router DOM
- Material UI (MUI)
- Axios
- JWT Authentication

---

## Project Structure

src/
├── api
│   ├── authApi.js
│   ├── transactionApi.js
│
├── pages
│   ├── Login.jsx
│   ├── Dashboard.jsx
│   ├── CreateTransaction.jsx
│   ├── Transactions.jsx
│
├── services
│   ├── axiosConfig.js
│   ├── tokenService.js

---

## Features

### Authentication

- Login with username and password
- JWT token stored in localStorage
- Automatic injection of token via Axios interceptor

---

### Dashboard

- Navigation hub
- Access to:
  - Create transaction
  - View transactions
  - Logout

---

### Create Transaction

- Form with:
  - Operación
  - Importe
  - Cliente
  - Secreto
- Success notification on creation
- Option to return to dashboard

---

### Transactions List

- Paginated table using MUI DataGrid
- Columns:
  - ID
  - Operación
  - Importe
  - Cliente
  - Status
- Cancel action for active transactions
- Cancelled transactions show no action button

---

## API Integration

Base URL:
http://localhost:8082/api/gateway

All requests require:

Authorization: Bearer <token>

---

## Environment Setup

### Install dependencies
npm install

### Run development server
npm run dev

App runs at:
http://localhost:5173

---

## Backend Dependency

This frontend requires the following backend services running:

- Gateway API (8082)
- Auth Service
- Transaction Service

---

## Notes

- Ensure CORS is enabled on backend for http://localhost:5173
- Ensure JWT token is correctly stored after login
- Ensure Axios interceptor is active for all protected routes

---

## Author

Carlos Caraveo  
Technical Assessment 2026