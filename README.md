# Spring Boot JWT Login

Implements POST /login using Spring Security, BCrypt, AuthenticationManager and JJWT HS256 signed tokens. Requires Java 17+ and Maven.

## Run
Open pom.xml as a Maven project in IntelliJ IDEA and run JwtApplication, or execute `mvn spring-boot:run`.

## Test in Postman
POST http://localhost:8080/login
Body > raw > JSON:
```json
{"username":"admin","password":"admin123"}
```
Expected: HTTP 200 with token, tokenType Bearer and expiresIn 3600. Wrong password returns 401; missing fields return 400.

The demo user is in memory. A random signing key is generated at startup unless JWT_SECRET is configured (Base64 encoded random key of at least 32 bytes). Tokens expire after one hour. This task issues tokens only; protected endpoint validation is outside its scope. For production replace demo credentials, configure a persistent secret securely, and use HTTPS.
