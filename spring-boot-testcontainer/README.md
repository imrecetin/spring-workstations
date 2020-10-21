https://blog.sandra-parsick.de/2020/05/21/using-testcontainers-in-spring-boot-tests-for-database-integration-tests/
https://reflectoring.io/spring-boot-flyway-testcontainers/

**Drawbacks**
have to install Docker on every machine where we want to build the application. 
    This could be a developer laptop or a CI build server.
tests interacting with Testcontainers are slower than the same test with an in-memory database, 
    because the Docker container has to be spun up.
