# Movie Catalog API

REST API application for managing movies, genres and directors.

The project provides CRUD operations, pagination, sorting, searching and dynamic filtering for movie catalog management.

---

# Technologies

- Java 21
- Spring Boot 3.5.16
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL 17
- Maven
- Lombok
- Bean Validation
- Swagger / OpenAPI
- Docker
- Docker Compose

---

# Architecture

The application follows a layered architecture:

```text
Controller
    |
    ↓
Service
    |
    ↓
Repository
    |
    ↓
PostgreSQL
```

## Layers description

### Controller

Handles HTTP requests and responses.

### Service

Contains business logic.

### Repository

Provides database access using Spring Data JPA.

### Entity

Represents database tables.

### DTO

Objects used for transferring data between client and server.

---

# Features

Implemented:

- Movie CRUD operations
- Genre CRUD operations
- Director CRUD operations
- Pagination
- Sorting
- Searching movies by title
- Dynamic filtering
- Request validation
- Global exception handling
- Swagger API documentation
- Dockerized application
- PostgreSQL database container

---

# Database Model

Main entities:

## Movie

Fields:

- title
- description
- release year
- rating
- duration
- genre
- director

## Genre

Fields:

- name

## Director

Fields:

- first name
- last name

Relationships:

```text
Movie
 |
 | ManyToOne
 |
Genre
```

```text
Movie
 |
 | ManyToOne
 |
Director
```

---

# API Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI specification:

```text
http://localhost:8080/v3/api-docs
```

---

# Running the Application

## Requirements

Installed:

- Java 21
- Docker Desktop

---

# Run with Docker Compose

Clone repository:

```bash
git clone <repository-url>
```

Go to project directory:

```bash
cd movie-catalog-api
```

Build and start containers:

```bash
docker compose up --build
```

Application will be available:

```text
http://localhost:8080
```

PostgreSQL runs inside Docker container.

---

# Environment Variables

Database configuration:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Example:

```text
DB_URL=jdbc:postgresql://postgres:5432/movie_catalog
DB_USERNAME=postgres
DB_PASSWORD=postgres
```

---

# API Endpoints

## Movies

```text
GET     /movies
GET     /movies/{id}
POST    /movies
PUT     /movies/{id}
DELETE  /movies/{id}
```

Additional filtering:

```text
GET /movies/filter
```

Supports:

- title search
- minimum rating
- minimum release year
- pagination
- sorting

---

## Genres

```text
GET     /genres
GET     /genres/{id}
POST    /genres
PUT     /genres/{id}
DELETE  /genres/{id}
```

---

## Directors

```text
GET     /directors
GET     /directors/{id}
POST    /directors
PUT     /directors/{id}
DELETE  /directors/{id}
```

---

# Future Improvements

Planned:

- Unit tests
- Integration tests
- Testcontainers
- Spring Security
- JWT authentication
- Role-based access control