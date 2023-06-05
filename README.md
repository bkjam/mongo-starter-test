# Create & Test Custom MongoDB Data Access Spring Boot Starter Library

This repository is to demonstrate how we can implement & test a custom data access library using Spring Boot Starter. You
can find the following code examples here:

- servlet & reactive implementations of custom Spring Boot Data Access Library for MongoDB
- how to test the data access libraries using embedded database with TestContainers

For more information, refer to the medium article here!

## Usage

### Testing the Data Access Library

```bash
gradle demo-mvc-mongodb-starter:test        # Test the custom MongoDB Spring Boot Starter Library
gradle demo-reactive-mongodb-starter:test   # Test the custom Reactive MongoDB Spring Boot Starter Library
```

### Run the Demo Service

```bash
# Start a MongoDB Server
docker run -d -p 27017:27017 --name mongo-server -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -e MONGO_INITDB_DATABASE=demoDB mongo:6.0.3

# Run the Demo Service
gradle demo-mvc-service:bootrun             # Spring Web (MVC) 
gradle demo-reactive-service:bootrun        # Spring Webflux (Reactive)
```

## References

- [https://nexocode.com/blog/posts/fast-stable-mongodb-tests-spring](https://nexocode.com/blog/posts/fast-stable-mongodb-tests-spring)
- [https://stackoverflow.com/questions/64991293/how-should-i-perform-integration-testing-on-a-springboot-library-that-isnt-an](https://stackoverflow.com/questions/64991293/how-should-i-perform-integration-testing-on-a-springboot-library-that-isnt-an)
- [https://stackoverflow.com/questions/58175467/testing-spring-boot-library-modules](https://stackoverflow.com/questions/58175467/testing-spring-boot-library-modules)
- [https://reflectoring.io/spring-boot-test/](https://reflectoring.io/spring-boot-test/)
