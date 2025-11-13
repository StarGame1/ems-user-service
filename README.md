# README

This microservice handles user management and synchronizes user records from the authentication service.

## Startup

- Local (Maven):
  - Build and run: mvnw spring-boot:run
- In Docker:
  - Build the JAR: mvnw -DskipTests package
  - Build and start with Docker Compose docker-compose up --build

## Main APIs

Base endpoint: /api/users

- POST /api/users

  - Description: Create a new user.
  - Body (JSON):
    - `username`: string (required)
    - `role`: string

- GET /api/users

  - Description: Return all users.

- GET /api/users/{id}

  - Description: Retrieve a user by id.

- PUT /api/users/{id}

  - Description: Update an existing user.

- DELETE /api/users/{id}
  - Description: Delete a user by id.

Internal endpoints:

- POST /internal/users

  - Description: Create or synchronize a user record coming from the authentication service.
  - Body (JSON): CreateUserRequest

## Internal notes

- Relevant entities:
  - `User` with fields `id`, `username`, `role`.
  - `Role` enum contains `ADMIN` and `CLIENT`.
- Controller: `UserController` exposes the public user endpoints.
- Controller: `InternalUserController` exposes the internal sync endpoint.
- Service: `UserService` contains user creation, lookup and synchronization logic.

## DTO examples

- CreateUserRequest :

```json
{
  "id": 1,
  "username": "alice",
  "role": "CLIENT"
}
```

- Create user request (public `POST /api/users`) :

```json
{
  "username": "alice",
  "role": "CLIENT"
}
```

- User response example :

```json
{
  "id": 1,
  "username": "alice",
  "role": "CLIENT"
}
```

## Database and Docker

- The project uses PostgreSQL
- Default application port: 8082

## Quick test

1. Start the database with Docker Compose :
   docker-compose up -d postgres-users-db
2. Build and run locally with Maven:
   mvnw spring-boot:run
3. Test endpoints with curl or Postman:
   - Get all users: GET http://localhost:8082/api/users
   - Create user: POST http://localhost:8082/api/users
   - Internal sync: POST http://localhost:8082/internal/users

## DTO examples (again)

- CreateUserRequest :

```json
{
  "id": 1,
  "username": "alice",
  "role": "CLIENT"
}
```

---

Completion summary: README updated to follow the exact structure of the example you provided, adapted for this microservice and without file paths.
