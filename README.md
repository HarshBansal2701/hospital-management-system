# Hospital Management System

A RESTful backend application for managing patients, doctors, departments, and hospital appointments.

This project is being developed using Java and Spring Boot with a focus on clean backend architecture, REST API design, database relationships, validation, exception handling, and transaction management.

## 🚀 Project Status

**In Development**

### Currently Implemented

- Patient CRUD APIs
- Doctor CRUD APIs
- Department CRUD APIs
- Doctor ↔ Department relationship management
- DTO-based request/response handling
- Request validation
- Global exception handling
- Custom business exceptions
- Transaction management
- JPA entity relationships
- MySQL database integration

### Currently Building

- Appointment management
- Automated testing
- Additional business rules

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Data access |
| Hibernate | ORM |
| MySQL | Relational database |
| Maven | Build and dependency management |
| Jakarta Bean Validation | Request validation |
| Lombok | Boilerplate reduction |

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
MySQL Database