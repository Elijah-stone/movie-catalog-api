# Movie Catalog API

REST API application for managing movies, genres and directors.

The project provides CRUD operations, pagination, sorting, searching and dynamic filtering for movie catalog management.

---

# Technologies

* Java 21
* Spring Boot 3.5.16
* Spring Web
* Spring Data JPA
* Hibernate
* PostgreSQL 17
* Maven
* Lombok
* Bean Validation
* Swagger / OpenAPI
* Docker
* Docker Compose
* JUnit 5
* Mockito
* MockMvc
* JaCoCo

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

Responsible for:

* request mapping;
* validation triggering;
* HTTP response generation.

### Service

Contains business logic.

Responsible for:

* entity processing;
* business rules validation;
* handling application exceptions.

### Repository

Provides database access using Spring Data JPA.

### Entity

Represents database tables.

### DTO

Objects used for transferring data between client and server.

### Mapper

Converts Entity objects to DTOs and DTOs to Entity objects.

---

# Features

Implemented:

* Movie CRUD operations
* Genre CRUD operations
* Director CRUD operations
* Pagination
* Sorting
* Searching movies by title
* Dynamic filtering using JPA Specification
* Request validation
* Global exception handling
* Swagger API documentation
* Dockerized application
* PostgreSQL database container
* Unit testing
* Controller testing
* Code coverage analysis

---

# Testing

The project contains automated tests for main application layers.

Covered:

## Service Layer

Tested:

* entity searching;
* entity creation;
* entity updating;
* entity deletion;
* exception handling.

Tools:

* JUnit 5
* Mockito

---

## Controller Layer

Tested using:

* MockMvc
* @WebMvcTest

Covered:

* GET requests;
* POST requests;
* PUT requests;
* DELETE requests;
* HTTP status codes;
* JSON responses;
* 404 error handling.

---

## Mapper Layer

Tested:

* Entity → DTO conversion;
* DTO → Entity conversion.

Covered mappers:

* MovieMapper;
* GenreMapper;
* DirectorMapper.

---

## Specification Layer

Tested dynamic filtering:

* searching by movie title;
* filtering by minimum rating;
* filtering by release year.

Testing performed with:

* @DataJpaTest;
* H2 database.

---

# Code Coverage

JaCoCo is used to analyze test coverage.

Current coverage:

**Total coverage: 82%**

Main modules:

| Module        | Coverage |
| ------------- | -------: |
| Controller    |     100% |
| Mapper        |     100% |
| Specification |      91% |
| Service       |      76% |
| Config        |     100% |
| DTO           |     100% |

---

# Database Model

Main entities:

## Movie

Fields:

* title
* description
* release year
* rating
* duration
* genre
* director

## Genre

Fields:

* name

## Director

Fields:

* first name
* last name

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

* Java 21
* Docker Desktop

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

* title search;
* minimum rating;
* minimum release year;
* pagination;
* sorting.

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

* Integration tests with Testcontainers
* CI/CD pipeline with GitHub Actions
* Spring Security
* JWT authentication
* Role-based access control
* Database migrations with Flyway or Liquibase
* Monitoring and application metrics
