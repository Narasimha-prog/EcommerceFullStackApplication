# 🌐 E-Commerce Full-Stack Monorepo

Welcome to the **E-Commerce Storefront**, a high-performance full-stack application built as a unified monorepo managed with **Nx**, separating enterprise-level business logic from active infrastructure delivery layers.

---

## 🏗️ Architectural Overview

This project enforces strict clean architectural boundaries between components to guarantee decoupling, high testability, and long-term maintainability.

### 🍃 Backend: Hexagonal Architecture & Domain-Driven Design (DDD)
The backend is powered by **Spring Boot 3.x / Java 21** and follows strict **DDD** and **Ports & Adapters** rules.
* **`domain/` (The Core):** Pure Java. Contains enterprise business models, aggregates (`Order`, `Product`, `User`), Mappers, Domain Services, Repositories (Interfaces), and immutable Value Objects (`vo/` like `OrderPrice`, `UserEmail`). It carries **zero framework dependencies** (no Spring annotations, no JPA).
* **`infrastructure/primary/` (Driving Adapters):** The REST API layer. Contains Controllers (`Resource` classes) and incoming request DTOs.
* **`infrastructure/secondary/` (Driven Adapters):** Outbound connections. Contains **JPA Entities**, Spring Data Database Repositories, database transaction controls, and external integrations (**Razorpay** for payment flows, **Kinde** for identity management).
* **`shared/`:** Contains reusable validation asserts (`Assert.java`), domain constraints, global exception handlers (`RestControllerAdvice`), and security configurations.

### 🅰️ Frontend: Reactive & Modern Angular Component Design
The user interface is powered by **Angular** combined with **Tailwind CSS**, focusing on decoupled feature modules.
* **`admin/` & `shop/`:** Clean module separation between administrative inventory ingestion tools and customer shopping utilities.
* **State & Services:** Reactive data structures managing cart caching configurations, payment processing gateways, and route authentication controls (`role-check-guard.ts`).

---

## 📁 Monorepo Directory Structure

```text
EcommerceFullStackApplication/
└── apps/
    ├── backend/                    # Spring Boot Application (Ports & Adapters)
    │   ├── src/main/java/com/lnr/ecom/
    │   │   ├── order/              # Order & User Aggregates + Razorpay Context
    │   │   ├── product/            # Product & Category Catalog Management
    │   │   └── shared/             # Enterprise Asserts, Security & Exception Interceptors
    │   ├── Dockerfile              # Containerized multi-stage wrapper build
    │   └── pom.xml                 # Maven Configuration
    └── frontend/                   # Client Application Architecture
        ├── src/app/
        │   ├── admin/              # Management Dashboards (Categories, Products, Orders)
        │   ├── shop/               # Client Interface (Filters, Cart, Checkout)
        │   └── auth/               # OAuth2 Middleware & SSR Token Storage
        ├── tailwind.config.js      # Utility-first UI theme specifications
        └── project.json            # Nx Target executor mappings
