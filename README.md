# Spring Boot Thymeleaf JDBC CRUD Demo

[![CI](https://github.com/victor-bendezu/springboot-thymeleaf-jdbc-crud-demo/actions/workflows/ci.yml/badge.svg)](https://github.com/victor-bendezu/springboot-thymeleaf-jdbc-crud-demo/actions/workflows/ci.yml)

A structured CRUD application built with Spring Boot, Thymeleaf and JdbcTemplate, following clean architecture practices and including validation handling, logging, controller testing and CI integration.

---

## 📸 Application Screenshots

### Main Screen

![Main Screen](docs/images/ui-main.png)

### Modal Form

![Modal](docs/images/ui-modal.png)

### H2 Console

![H2 Console](docs/images/h2-console.png)

---

## 🚀 Tech Stack

- Java 17
- Spring Boot
- Thymeleaf
- JdbcTemplate (SP-style approach)
- H2 Database (embedded)
- Log4j2
- JUnit 5
- MockMvc
- GitHub Actions (CI)

---

## 🏗 Architecture Overview

This project follows a layered architecture:

Controller → Service → DAO → Database

Additional cross-cutting concerns implemented:

- Global exception handling
- Structured logging
- Request correlation via MDC filter
- Validation error handling at controller level
- API response standardization

---

## ✅ Implemented Features

- Full CRUD for Product Type
- Server-side validation with structured error handling
- Standardized API responses
- GlobalExceptionHandler for validation errors
- Log4j2 structured logging configuration
- RequestId MDC filter for request traceability
- Controller layer unit tests with MockMvc
- GitHub Actions CI pipeline

---

## 🧪 Testing

Controller layer tests implemented using:

- @WebMvcTest
- MockMvc
- Mockito

Tests validate:

- Validation error responses
- Successful delete operation behavior

To run tests:

    mvn test

---

## 🔄 CI Integration

GitHub Actions workflow automatically:

- Builds the project
- Runs tests
- Validates successful compilation

Workflow file located at:

    .github/workflows/ci.yml

---

## ▶️ Running the Application

    mvn spring-boot:run

Then access:

    http://localhost:8080/product-type

H2 Console:

    http://localhost:8080/h2-console

---

## 🎯 What This Project Demonstrates

- Layered backend architecture
- Validation handling best practices
- Structured logging configuration
- Global exception management
- Controller-level unit testing
- Basic CI pipeline integration
- Clean Git workflow (feature branch → PR → merge)

This repository is intended to demonstrate backend development practices using Spring Boot in a structured and production-minded approach.
