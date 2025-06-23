# apitrial

A simple Spring Boot REST API demo for processing transactions with basic validation and request logging using an interceptor.

## Features

- **POST /pay** endpoint for processing transactions
- Validates username and amount
- Logs incoming requests using a Spring MVC interceptor
- Clean project structure with separation of concerns

## Project Structure

```
src/main/java/com/apitrial/apitrial/
├── ApitrialApplication.java           # Main Spring Boot application
├── config/
│   └── WebMvcConfig.java              # Registers the LoggingInterceptor
├── controller/
│   └── TransactionController.java     # Handles /pay endpoint
├── interceptor/
│   └── LoggingInterceptor.java        # Logs request details
└── model/
    └── TransactionRequest.java        # Model for transaction request body
```

## Getting Started

### Prerequisites

- Java 21
- Maven

### Build and Run

```bash
mvn spring-boot:run
```

The application will start at [http://localhost:8080](http://localhost:8080).

## API Usage

### Endpoint

- **POST** `/pay`

#### Request Body

```json
{
  "username": "string",
  "amount": 100
}
```

#### Responses

- `200 OK`: `"transaction feasible"` (if valid)
- `403 FORBIDDEN`: `"XX - Banned User"` (if username is "banned")
- `400 BAD REQUEST`: `"XX - Amount exceeds limit"` (if amount > 1000)

### Example cURL

```bash
curl -X POST http://localhost:8080/pay \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "amount": 100}'
```

## Logging

All incoming requests are logged by `LoggingInterceptor`. You can view logs in the console output.

## License

This project is for