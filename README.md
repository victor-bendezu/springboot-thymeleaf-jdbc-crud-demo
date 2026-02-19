# Spring Boot + Thymeleaf + JDBC CRUD Demo (Portfolio)

This repository is a **portfolio project** designed to showcase a stack I have hands-on experience with:

- Spring Boot (embedded Tomcat)
- Thymeleaf (server-side rendering)
- jQuery + AJAX
- Layered MVC structure: controller / service / serviceimpl / dao / daoimpl
- JdbcTemplate
- Stored-procedure style patterns (flag-based result)
- ApiResponse wrapper + GlobalExceptionHandler (controllers without try/catch)

> This is a demo project. It does **not** contain any client code or proprietary business logic.

---

## Features

- Product Type maintenance (list / create / edit / delete)
- Preloaded dropdown: Product Classification
- Single-page UI with modal form and AJAX calls

---

## Architecture Overview

This project follows a layered MVC architecture commonly used in enterprise applications:

- **Controller layer**: view mapping + REST endpoints
- **Service layer**: business logic + stored-procedure style result validation
- **DAO layer**: `JdbcTemplate` access with SP-style calls
- **Global error handling**: centralized exceptions via `@RestControllerAdvice`
- **Response wrapper**: consistent JSON responses using `ApiResponse<T>`

---

## Running locally

### IntelliJ IDEA
1. Open the project as a Maven project
2. Run `PortfolioCrudDemoApplication`
3. Open: `http://localhost:8080/product-type`

### Command line
```bash
mvn spring-boot:run
