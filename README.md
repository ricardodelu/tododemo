# Todo Demo - Java Spring Boot REST API

A minimal REST API for blog posts, built with Java Spring Boot and designed for deployment on Railway with PostgreSQL.

## Features

- Create, read, and delete blog posts
- Spring Boot with Spring Data JPA
- PostgreSQL database support
- H2 in-memory database for local development
- Railway deployment ready

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/posts` | Create a new blog post |
| GET | `/posts` | Get all blog posts |
| GET | `/posts/{id}` | Get a specific blog post by ID |
| DELETE | `/posts/{id}` | Delete a blog post by ID |

### Request/Response Examples

#### Create a Post
```bash
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My First Post",
    "content": "This is the content of my first post.",
    "author": "John Doe"
  }'
```

#### Get All Posts
```bash
curl http://localhost:8080/posts
```

#### Get Post by ID
```bash
curl http://localhost:8080/posts/1
```

#### Delete Post
```bash
curl -X DELETE http://localhost:8080/posts/1
```

## Local Development

### Prerequisites
- Java 17 or later
- Maven 3.6 or later

### Running Locally
1. Clone the repository
2. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
3. The application will start on `http://localhost:8080`
4. Access H2 database console at `http://localhost:8080/h2-console` (if enabled)

### Running Tests
```bash
./mvnw test
```

## Railway Deployment

This application is configured for easy deployment on Railway with a managed PostgreSQL database.

### Step 1: Connect Repository to Railway

1. **Sign up/Sign in to Railway**: Visit [railway.app](https://railway.app) and create an account or sign in
2. **Create a New Project**: Click "New Project" from your Railway dashboard
3. **Deploy from GitHub**: Select "Deploy from GitHub repo" and choose this repository
4. **Grant Permissions**: Allow Railway to access your GitHub repository

### Step 2: Provision PostgreSQL Database

1. **Add PostgreSQL Service**: In your Railway project dashboard, click "Add Service" → "Database" → "PostgreSQL"
2. **Database Created**: Railway will automatically provision a PostgreSQL database
3. **Note the Variables**: Railway will automatically create environment variables for database connection:
   - `DATABASE_URL` - Complete PostgreSQL connection string
   - `PGDATABASE` - Database name
   - `PGHOST` - Database host
   - `PGPASSWORD` - Database password
   - `PGPORT` - Database port
   - `PGUSER` - Database username

### Step 3: Configure Environment Variables

The application automatically uses the `DATABASE_URL` environment variable provided by Railway. No additional configuration is needed for the database connection.

**Required Environment Variables** (automatically set by Railway):
- `DATABASE_URL` - PostgreSQL connection string (format: `postgresql://user:password@host:port/database`)
- `PORT` - Port for the web service (Railway sets this automatically)

**Optional Environment Variables**:
- `SPRING_PROFILES_ACTIVE=prod` - Use production profile (recommended for Railway)

### Step 4: Deploy

1. **Automatic Deployment**: Railway will automatically build and deploy your application when you push to the main branch
2. **Build Process**: Railway uses the included `railway.json` configuration and `Procfile`
3. **Access Your App**: Once deployed, Railway will provide a public URL for your application

### Step 5: Test the Deployment

1. **Get the Public URL**: Find your app's URL in the Railway dashboard
2. **Test the API**: Use the API endpoints with your Railway URL:
   ```bash
   # Replace YOUR_RAILWAY_URL with your actual Railway app URL
   curl -X POST https://YOUR_RAILWAY_URL/posts \
     -H "Content-Type: application/json" \
     -d '{
       "title": "Test from Railway",
       "content": "Testing the deployed API",
       "author": "Railway User"
     }'
   ```

## Configuration Files

### `railway.json`
Configures Railway build and deployment settings:
```json
{
  "build": {
    "builder": "NIXPACKS"
  },
  "deploy": {
    "startCommand": "java -jar target/tododemo-0.0.1-SNAPSHOT.jar --server.port=$PORT"
  }
}
```

### `Procfile`
Alternative deployment configuration:
```
web: java -jar target/tododemo-0.0.1-SNAPSHOT.jar --server.port=$PORT --spring.profiles.active=prod
```

### Environment-based Configuration

- **Local Development**: Uses H2 in-memory database (default profile)
- **Production**: Uses PostgreSQL database (prod profile)

The application automatically detects the environment and uses appropriate database configuration.

## Database Schema

The application uses JPA/Hibernate to automatically create and manage the database schema.

**Post Entity**:
- `id` (Long) - Primary key, auto-generated
- `title` (String) - Post title, required
- `content` (Text) - Post content
- `author` (String) - Post author, required
- `created_at` (Timestamp) - Creation timestamp, auto-generated

## Troubleshooting

### Common Issues

1. **Build Fails**: Ensure Java 17+ is configured in your Railway settings
2. **Database Connection Issues**: Verify `DATABASE_URL` environment variable is set
3. **Port Issues**: Make sure the application is listening on `$PORT` environment variable

### Logs
View application logs in the Railway dashboard under your service's "Logs" tab.

### Health Check
Test if your application is running:
```bash
curl https://YOUR_RAILWAY_URL/posts
```

## Technology Stack

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **PostgreSQL** (production)
- **H2 Database** (development)
- **Maven** (build tool)
- **Railway** (deployment platform)

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests: `./mvnw test`
5. Commit and push changes
6. Create a Pull Request

## License

This project is for demonstration purposes.