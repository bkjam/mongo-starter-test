# Testing Custom MongoDB Data Access Starter Library

This repository is to demonstrate how we can implement and test a custom database (mongoDB) Spring Boot Starter Library. 
For more information, refer to the blog post I have on medium below.

## Usage

### Testing

```bash
# Test MongoDB Starter Library
gradle demo-mongodb-starter:test

# Test Service (that implements MongoDB Starter Library)
gradle demo-service:test
```

### Execute

```bash
# Start a Mongo Server
docker run -d -p 27017:27017 --name mongo-server mongo:5.0.10

# Run the Demo Service
gradle demo-service:bootrun
```
