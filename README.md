# credi-card-system-backend
Credit Card System Backend Application

The following document provides details of Credit Card Processing Application Backend Microservice implemented in springboot.

# Getting Started

The project uses maven for dependency management.

### To build the project

In Linux Machine
```shell script
chmod 755 mvnw
./mvnw clean install
```

In Windows Machine
```shell script
mvnw.cmd clean install
```

### To Run the Unit Tests
In Linux Machine
```shell script
chmod 755 mvnw
./mvnw clean test
```
In Windows Machine
```shell script
mvnw.cmd clean test
```
### To Run the application
In Linux Machine
```shell script
chmod 755 mvnw
./mvnw spring-boot:run
```
In Windows Machine
```shell script
mvnw.cmd spring-boot:run
```

### API Documentation

The Swagger page provides the open api spec for the APIs. Please use the below URL to get to the Swagger Page.
```html
    <a href="http://localhost:8080/swagger-ui.html" target="_blank"> Swagger URL </a>
```

### Test Data

#### Create Credit Card
```json
{
    "creditCardNumber": "4444333322221111",
    "customerName":    "Jyothi",
    "creditCardLimit": 1200.00
}
```


