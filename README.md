# Spring Boot Microservices – Real World Backend Project

🚧 **Project Status: In Progress (Actively Developing)**

This repository contains a real-world backend microservices system built step-by-step to prepare for backend (SDE-1 / SDE-2) interviews.  
The focus is on **clean architecture, scalability, observability, and industry best practices**, not just CRUD APIs.

---

## 🎯 Project Objective

To design and implement a **production-style microservices backend** using Spring Boot and Spring Cloud, covering:

- Service discovery
- Centralized configuration
- API Gateway
- Inter-service communication
- Observability (metrics & monitoring)
- Logging, resilience, messaging, security (planned)

This project is intentionally built **incrementally**, similar to how real systems evolve in companies.

---

## 🛠️ Tech Stack

- Java 17  
- Spring Boot 3.2.x  
- Spring Cloud 2023.x  
- Spring Data JPA  
- MySQL  
- Spring Cloud Eureka  
- Spring Cloud Config Server  
- Spring Cloud Gateway  
- OpenFeign  
- Prometheus  
- Grafana  

---

## 🧩 Microservices Overview

| Service | Description |
|------|------------|
| **Service Registry** | Eureka Server for service discovery |
| **Config Server** | Centralized configuration management |
| **API Gateway** | Single entry point for all client requests |
| **Employee Service** | Manages employee data (CRUD) |
| **Department Service** | Manages department data (CRUD) |

---

## 🏗️ Architecture Overview

- **Eureka Server** handles service registration and discovery  
- **API Gateway** is the only public entry point (north-south traffic)  
- **Employee ↔ Department** communication uses **Feign Client**  
- **Spring Cloud Config Server** provides centralized configuration  
- **Client-side load balancing** via Spring Cloud LoadBalancer  
- **Observability** implemented using Prometheus & Grafana  

This architecture follows **real production microservice patterns**.

---

## 🔍 Observability (Implemented)

- Spring Boot Actuator enabled
- Prometheus scraping application metrics
- Grafana dashboards for:
  - JVM metrics
  - CPU & memory usage
  - HTTP request metrics
  - p95 / p99 latency
  - Error rate monitoring (4xx vs 5xx)

> Micrometer HTTP histograms are explicitly enabled for accurate latency percentiles.

---
# Spring Boot Microservices – Real World Backend Project

🚧 **Project Status: In Progress (Actively Developing)**

This repository contains a real-world backend microservices system built step-by-step to prepare for backend (SDE-1 / SDE-2) interviews.  
The focus is on **clean architecture, scalability, observability, and industry best practices**, not just CRUD APIs.

---

## 🎯 Project Objective

To design and implement a **production-style microservices backend** using Spring Boot and Spring Cloud, covering:

- Service discovery
- Centralized configuration
- API Gateway
- Inter-service communication
- Observability (metrics & monitoring)
- Logging, resilience, messaging, security (planned)

This project is intentionally built **incrementally**, similar to how real systems evolve in companies.

---

## 🛠️ Tech Stack

- Java 17  
- Spring Boot 3.2.x  
- Spring Cloud 2023.x  
- Spring Data JPA  
- MySQL  
- Spring Cloud Eureka  
- Spring Cloud Config Server  
- Spring Cloud Gateway  
- OpenFeign  
- Prometheus  
- Grafana  

---

## 🧩 Microservices Overview

| Service | Description |
|------|------------|
| **Service Registry** | Eureka Server for service discovery |
| **Config Server** | Centralized configuration management |
| **API Gateway** | Single entry point for all client requests |
| **Employee Service** | Manages employee data (CRUD) |
| **Department Service** | Manages department data (CRUD) |

---

## 🏗️ Architecture Overview

- **Eureka Server** handles service registration and discovery  
- **API Gateway** is the only public entry point (north-south traffic)  
- **Employee ↔ Department** communication uses **Feign Client**  
- **Spring Cloud Config Server** provides centralized configuration  
- **Client-side load balancing** via Spring Cloud LoadBalancer  
- **Observability** implemented using Prometheus & Grafana  

This architecture follows **real production microservice patterns**.

---

## 🔍 Observability (Implemented)

- Spring Boot Actuator enabled
- Prometheus scraping application metrics
- Grafana dashboards for:
  - JVM metrics
  - CPU & memory usage
  - HTTP request metrics
  - p95 / p99 latency
  - Error rate monitoring (4xx vs 5xx)

> Micrometer HTTP histograms are explicitly enabled for accurate latency percentiles.

---

## 🔗 Accessing the Application (Local Setup)

This project runs multiple Spring Boot microservices locally.

### General Access Pattern

- **Eureka Server**  
  Used for service discovery and instance monitoring.

- **API Gateway**  
  Acts as the single public entry point for all client requests.  
  All business APIs (Employee, Department, etc.) are accessed **via the Gateway**.

- **Config Server**  
  Provides centralized configuration to all microservices at startup.

- **Business Services (Employee, Department)**  
  These services register with Eureka and communicate internally using Feign Client.  
  Direct access is mainly for internal testing; external access is routed through the API Gateway.

- **Actuator Endpoints**  
  Each service exposes actuator endpoints for health checks and metrics.

- **Prometheus & Grafana**  
  Used for metrics collection and visualization (JVM, HTTP, latency, errors).

> ⚠️ Exact URLs and ports may vary depending on local configuration and are intentionally not hardcoded here.

---

## ⚙️ Configuration Management

- Application code and centralized configuration are maintained in **separate Git repositories**
- Configurations are managed using **Spring Cloud Config Server**
- Each microservice has its own YAML configuration file

This separation follows **industry best practices**.

---

## ✅ Features Implemented So Far

- Service Discovery (Eureka)
- Centralized Configuration (Config Server)
- API Gateway routing
- Inter-service communication using Feign
- Client-side load balancing
- Observability with Prometheus & Grafana
- Proper Git repository structure

---

## 🚧 Work in Progress

- Centralized logging
- Request & error logging
- Correlation ID for request tracing

---

## 🔮 Planned Enhancements

- Resilience patterns (Retry, Circuit Breaker, Timeouts)
- Event-driven communication using Kafka
- Pagination & validation
- Performance optimization & caching
- Security using Spring Security & JWT
- Docker & Kubernetes concepts
- CI/CD pipeline explanation

---

## 👨‍💻 Author

**Praful Jain**  
Backend Developer | Java | Spring Boot | Microservices  

This project is part of continuous backend engineering learning and interview preparation.

---

## 📌 Note

This project is intentionally **not marked as complete**.  
The README is updated **incrementally** as new features are implemented, reflecting real-world development workflows.





---

## ⚙️ Configuration Management

- Application code and centralized configuration are maintained in **separate Git repositories**
- Configurations are managed using **Spring Cloud Config Server**
- Each microservice has its own YAML configuration file

This separation follows **industry best practices**.

---

## ✅ Features Implemented So Far

- Service Discovery (Eureka)
- Centralized Configuration (Config Server)
- API Gateway routing
- Inter-service communication using Feign
- Client-side load balancing
- Observability with Prometheus & Grafana
- Proper Git repository structure

---

## 🚧 Work in Progress

- Centralized logging
- Request & error logging
- Correlation ID for request tracing

---

## 🔮 Planned Enhancements

- Resilience patterns (Retry, Circuit Breaker, Timeouts)
- Event-driven communication using Kafka
- Pagination & validation
- Performance optimization & caching
- Security using Spring Security & JWT
- Docker & Kubernetes concepts
- CI/CD pipeline explanation

---

## 👨‍💻 Author

**Praful Jain**  
Backend Developer | Java | Spring Boot | Microservices  

This project is part of continuous backend engineering learning and interview preparation.

---

## 📌 Note

This project is intentionally **not marked as complete**.  
The README is updated **incrementally** as new features are implemented, reflecting real-world development workflows.
