# Spring Boot Thymeleaf JDBC CRUD Demo (Product Type)

[![CI](https://github.com/victor-bendezu/springboot-thymeleaf-jdbc-crud-demo/actions/workflows/ci.yml/badge.svg)](https://github.com/victor-bendezu/springboot-thymeleaf-jdbc-crud-demo/actions/workflows/ci.yml)
[![Render](https://img.shields.io/badge/Live-Demo-46E3B7?logo=render&logoColor=white)](https://springboot-thymeleaf-jdbc-crud-demo.onrender.com/product-type)

A portfolio MVC web application demonstrating practical Spring Boot
fundamentals including layered architecture, server-side rendering with
Thymeleaf, validation handling, structured logging, automated testing,
and cloud deployment.

This project showcases practical engineering skills applied to a
traditional server-side rendered web application, focusing on clean
layering (Controller → Service → DAO), maintainability, consistency, and
industry-aligned best practices suitable for a portfolio demo.

------------------------------------------------------------------------

## 🎯 Purpose

This project is part of my portfolio to demonstrate practical Spring Boot development skills,
structured layering (Controller → Service → DAO), validation fundamentals, automated testing, 
CI/CD integration, and clean architecture principles applied to a web MVC application
(Thymeleaf + jQuery AJAX).

------------------------------------------------------------------------

## 🔧 Tech Stack

-   Java 17
-   Spring Boot 3
-   Thymeleaf (server-side rendering)
-   JdbcTemplate (DAO layer)
-   H2 (in-memory database)
-   Log4j2 (structured logging)
-   JUnit 5
-   MockMvc
-   Maven (wrapper included)
-   Docker (multi-stage build)
-   GitHub Actions (CI pipeline)
-   Render (cloud deployment)

------------------------------------------------------------------------

## 🌍 Live Demo (Cloud Deployment)

Application:
https://springboot-thymeleaf-jdbc-crud-demo.onrender.com/product-type

Note: Free instances spin down after inactivity, so the first request
may take a few seconds to respond.

------------------------------------------------------------------------

## 🏗 Architecture

Layered design:

View (Thymeleaf) → Controller → Service → DAO (JdbcTemplate) → H2 DB

Key features:

-   MVC endpoint rendering (Thymeleaf template)
-   AJAX endpoints returning a consistent JSON response wrapper
-   Server-side validation (Bean Validation) + standardized error handling
-   Global exception handling (unexpected errors)
-   Structured logging with request correlation (MDC requestId)

![Diagram](docs/images/architecture-diagram.png)

------------------------------------------------------------------------

## 🔐 Validation & Error Handling

This demo focuses on validation and clean server-side error handling:

-   Bean Validation on request DTOs
-   Validation errors mapped to a standardized JSON response
-   Global exception handler provides consistent error responses for unexpected failures

------------------------------------------------------------------------

## 📦 Public Endpoints

**UI**

-   GET `/product-type` (main screen)

**AJAX / JSON**

-   GET `/api/product-type`
-   GET `/api/classifications`
-   POST `/api/product-type`
-   PUT `/api/product-type/{id}`
-   DELETE `/api/product-type/{id}`

**Dev-only (local)**

-   H2 Console: `/h2-console`

Note: The demo uses an in-memory H2 database. Data resets when the
application restarts.

------------------------------------------------------------------------

## 🚀 Quick Start (Local)

Requirements:

-   Java 17
-   Maven 3.9+ (or use included Maven Wrapper)

Run:

`mvn clean spring-boot:run`

or

`./mvnw clean spring-boot:run`

App runs at:

http://localhost:8080/product-type

------------------------------------------------------------------------

## ⚡ Quick Start (Docker)

`docker compose up --build`

Stop:

`docker compose down`

------------------------------------------------------------------------

## 🧪 Testing

Unit and integration tests are included using Spring Boot Test and MockMvc.

Validated areas:

- MVC view rendering (`ProductTypeViewControllerTest`)
- REST controller behavior (`ProductTypeControllerWebMvcTest`)
- DAO behavior with JdbcTemplate against H2 (`ProductTypeDaoJdbcTest`)
- Service behavior (mocked DAO) (`ProductTypeServiceImplTest`)
- End-to-end API integration (H2 + MVC) (`ProductTypeApiIntegrationTest`)
- Global exception handling standardization (`ProductTypeGlobalExceptionHandlerTest`)

Run tests:

`mvn test`

or

`./mvnw clean verify`

Tests execute automatically via GitHub Actions on every push.

------------------------------------------------------------------------

## 🛠 Deployment Overview

The project is containerized using a multi-stage Docker build:

-   Maven build stage
-   Lightweight JRE runtime stage
-   Environment-based port configuration (`server.port=${PORT:8080}`)
-   HTTPS termination handled by Render
-   CI validates builds and tests on every push

In production, H2 would be replaced by a persistent database
(PostgreSQL/MySQL) with externalized configuration.

------------------------------------------------------------------------

## 📸 Screenshots

### Main Screen

![Main Screen](docs/images/ui-main.png)

### Modal Form

![Modal](docs/images/ui-modal.png)

### H2 Console

![H2 Console](docs/images/h2-console.png)

------------------------------------------------------------------------

## 🧠 Design Notes

-   MVC UI rendered via Thymeleaf, with AJAX calls for CRUD operations
-   Centralized exception handling via `GlobalExceptionHandler`
-   Consistent JSON responses via `ApiResponse<T>`
-   Log4j2 configuration with MDC requestId for traceability
-   Clean separation of responsibilities across layers

------------------------------------------------------------------------

## 🧩 Project Structure

```text
src/main/java/com/victor/portfolio
├── common
│   └── web
├── db
├── product
│   ├── controller
│   ├── dao
│   ├── domain
│   ├── dto
│   └── service
└── PortfolioCrudDemoApplication.java

src/main/resources
├── static
├── templates
├── application.yml
├── application-dev.yml
└── application-prod.yml
```

This structure keeps responsibilities separated by feature (`product`)
and by layer (`controller/service/dao`), making the project easier to
navigate and maintain.

------------------------------------------------------------------------

## ⚙ Environment Configuration

The application supports profile-based configuration:

-   application.yml
-   application-dev.yml
-   application-prod.yml

The server port is configurable via:

`server.port=${PORT:8080}`

This enables flexible local and cloud deployment configuration.

------------------------------------------------------------------------