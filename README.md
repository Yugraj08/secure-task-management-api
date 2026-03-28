# Secure Task Management REST API

##  Overview

This project is a scalable REST API built using Spring Boot that provides user authentication and task management functionality. It implements JWT-based authentication and role-based access control.

---

##  Features

* User Registration & Login
* Password Hashing using BCrypt
* JWT Authentication
* Role-Based Access (USER / ADMIN)
* CRUD Operations for Tasks
* Global Exception Handling
* API Versioning (/api/v1)

---

##  Tech Stack

* Java 17
* Spring Boot
* Spring Security
* JWT (jjwt)
* MySQL
* Maven

---

##  Project Structure

controller/ → API endpoints
service/ → Business logic (if any)
repository/ → Database interaction
entity/ → Database models
security/ → JWT & security config
exception/ → Global error handling

---

##  How to Run

1. Clone the repository

2. Create MySQL database:

   ```sql
   create database intern_db;
   ```

3. Update application.properties:

    * username
    * password

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

---

##  Authentication Flow

1. Register user → `/api/v1/auth/register`
2. Login → `/api/v1/auth/login`
3. Receive JWT token
4. Use token in headers:

   ```
   Authorization: Bearer <token>
   ```

---

##  API Endpoints

###  Auth

* POST `/api/v1/auth/register`
* POST `/api/v1/auth/login`

###  Tasks

* POST `/api/v1/tasks` → Create Task
* GET `/api/v1/tasks` → Get Tasks
* PUT `/api/v1/tasks/{id}` → Update Task
* DELETE `/api/v1/tasks/{id}` → Delete Task

---

##  API Testing

Postman collection is included in the repository for testing all endpoints.

---

##  Scalability Notes

* Can be extended into microservices architecture
* JWT allows stateless authentication
* Database can be scaled using indexing and replication
* Caching (Redis) can be added for performance improvement

---
## Frontend Note
A basic frontend can be integrated using React or any frontend framework to consume these APIs. Due to time constraints, the focus was kept on building a robust and scalable backend system.

##  Author

Yugraj Mewara
