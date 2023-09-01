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

## Other Common Issues

### Repository Bean Not Found

```bash
***************************
APPLICATION FAILED TO START
***************************

Description:
Parameter 0 of constructor in com.example.data.features.demo.DemoDao
required a bean of type 'com.example.data.features.demo.DemoRepository' 
that could not be found.

Action:
Consider defining a bean of type 
'com.example.data.features.demo.DemoRepository' in your configuration.
```

This probably occurs because the package name for the starter library is different from the Spring Boot application that
is consuming it. Eg. `com.example.data` vs `com.example.app`. To resolve it, ensure that in the auto-configuration class,
add `@EnableMongoRepositories`.

```kotlin
@Configuration
@EnableMongoRepositories(basePackages = ["com.example.data"])
@ComponentScan("com.example.data")
class MongoAutoConfiguration
```

### DemoDAO has not been initialized

```bash
lateinit property demoDao has not been initialized
kotlin.UninitializedPropertyAccessException: lateinit property demoDao has not been initialized
```

This error occurs when you are running your test cases without `@MongoSpringBootTest` annotation as the test cases do not
know where the packages are found. Eg.

```kotlin
@Configuration
@ComponentScan("com.example.data")
class TestConfig
```

### MongoTemplate Bean Not Found

```bash
***************************
APPLICATION FAILED TO START
***************************
Description:
Parameter 0 of constructor in com.example.database.dao.DemoDao required a bean named 'mongoTemplate' that could not be found.
Action:
Consider defining a bean named 'mongoTemplate' in your configuration.
```

This error occurs because the tests cases are not configured with relevant configurations for MongoDB tests. Make sure that
in your `@MongoSpringBootTest`, you add the annotation `@AutoConfigureDataMongo`. Eg.

```kotlin
@Target(AnnotationTarget.CLASS)
@SpringBootTest(classes = [TestConfig::class])
@ContextConfiguration(initializers = [MongoInitializer::class])
@AutoConfigureDataMongo
annotation class MongoSpringBootTest
```

## References

- [https://nexocode.com/blog/posts/fast-stable-mongodb-tests-spring](https://nexocode.com/blog/posts/fast-stable-mongodb-tests-spring)
- [https://stackoverflow.com/questions/64991293/how-should-i-perform-integration-testing-on-a-springboot-library-that-isnt-an](https://stackoverflow.com/questions/64991293/how-should-i-perform-integration-testing-on-a-springboot-library-that-isnt-an)
- [https://stackoverflow.com/questions/58175467/testing-spring-boot-library-modules](https://stackoverflow.com/questions/58175467/testing-spring-boot-library-modules)
- [https://reflectoring.io/spring-boot-test/](https://reflectoring.io/spring-boot-test/)
