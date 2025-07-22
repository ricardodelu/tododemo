# Technical Specification: Java Blog Backend Demo

## Overview
A minimal REST API for blog posts, built with Java Spring Boot and PostgreSQL, deployed on Railway.

## Stack
- **Language:** Java 17+
- **Framework:** Spring Boot
- **Database:** PostgreSQL (Railway managed)
- **Build Tool:** Maven

## Architecture
- RESTful API with endpoints for blog post CRUD.
- Repository pattern using Spring Data JPA.

## Endpoints
- `POST /posts` — Create a new post
- `GET /posts` — List all posts
- `GET /posts/{id}` — Get a post by ID
- `DELETE /posts/{id}` — Delete a post by ID

## Data Model
**Post**
- `id` (Long, auto-generated)
- `title` (String)
- `content` (String)
- `author` (String)
- `createdAt` (Timestamp)

## Database
- Use Railway’s PostgreSQL add-on.
- Configure connection via environment variables:
  - `SPRING_DATASOURCE_URL`
  - `SPRING_DATASOURCE_USERNAME`
  - `SPRING_DATASOURCE_PASSWORD`

## Deployment
- Push code to GitHub.
- Connect Railway project to GitHub repo.
- Set up Railway environment variables for DB connection.
- Deploy via Railway dashboard.

## Testing
- Manual testing via Postman or curl.
- (Optional) Basic unit tests for controller and repository.

## Notes
- No authentication or advanced features.
- Focus on deployment and DB connectivity.