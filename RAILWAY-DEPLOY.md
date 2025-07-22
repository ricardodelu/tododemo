# Railway Deployment Quick Reference

## Environment Variables Required by Railway

When deploying to Railway, the following environment variables will be automatically provided by the platform:

### Database Connection
- `DATABASE_URL` - Complete PostgreSQL connection string (automatically set by Railway when you add PostgreSQL service)
- Format: `postgresql://username:password@host:port/database`

### Server Configuration  
- `PORT` - Port number for the web service (automatically set by Railway)

### Optional Configuration
- `SPRING_PROFILES_ACTIVE=prod` - Enables production profile (recommended)

## Railway Service Configuration

1. **Web Service**: Your Spring Boot application
   - Uses `railway.json` or `Procfile` for deployment configuration
   - Automatically builds with Maven
   - Listens on `$PORT` environment variable

2. **PostgreSQL Service**: Managed database
   - Automatically provisions database
   - Sets `DATABASE_URL` and related variables
   - No additional configuration needed

## Quick Deployment Checklist

- [ ] Repository connected to Railway
- [ ] PostgreSQL service added to project
- [ ] Web service deployed from repository
- [ ] `DATABASE_URL` environment variable is set (automatic)
- [ ] `PORT` environment variable is set (automatic)
- [ ] Application builds successfully
- [ ] Application starts without errors
- [ ] API endpoints respond correctly

## Testing Deployment

Once deployed, test your Railway app with:

```bash
# Replace YOUR_RAILWAY_URL with your actual Railway app URL
export RAILWAY_URL="https://your-app-name.railway.app"

# Test create post
curl -X POST $RAILWAY_URL/posts \
  -H "Content-Type: application/json" \
  -d '{"title": "Railway Test", "content": "Testing Railway deployment", "author": "Railway"}'

# Test get all posts
curl $RAILWAY_URL/posts

# Test get specific post (replace {id} with actual post ID)
curl $RAILWAY_URL/posts/{id}

# Test delete post (replace {id} with actual post ID)
curl -X DELETE $RAILWAY_URL/posts/{id}
```

## Common Issues

1. **Build fails**: Check Java version is 17+ in Railway settings
2. **Database connection issues**: Verify PostgreSQL service is added and `DATABASE_URL` is set
3. **App not starting**: Check logs for port binding issues
4. **404 errors**: Verify application is running and endpoints are correct