# apitrial

A simple Spring Boot REST API demo for processing transactions with validation, request logging (via interceptor), and full request/response body logging (via filter).

---

## How the Application Works

### 1. **Application Startup**

- The entry point is [`ApitrialApplication.java`](src/main/java/com/apitrial/apitrial/ApitrialApplication.java), which starts the Spring Boot application.
- Spring Boot scans all classes under the `com.apitrial.apitrial` package for components, configurations, controllers, etc.

---

### 2. **Handling a Transaction Request**

- The REST endpoint `/pay` is defined in [`TransactionController.java`](src/main/java/com/apitrial/apitrial/controller/TransactionController.java).
- When a POST request is made to `/pay` with a JSON body like:
  ```json
  {
    "username": "testuser",
    "amount": 100
  }
  ```
  the controller receives it as a `TransactionRequest` object.

- The controller applies business rules:
  - If `username` is `"banned"`, it returns `403 Forbidden`.
  - If `amount` is greater than `1000`, it returns `400 Bad Request`.
  - Otherwise, it returns `200 OK` with `"transaction feasible"`.

---

### 3. **Logging with Interceptor**

- [`LoggingInterceptor.java`](src/main/java/com/apitrial/apitrial/interceptor/LoggingInterceptor.java) is registered via [`WebMvcConfig.java`](src/main/java/com/apitrial/apitrial/config/WebMvcConfig.java).
- For every HTTP request, the interceptor logs:
  - Request URL and method (before controller)
  - Response status (after controller)
- **Note:** The interceptor does not log the request body, as it is not accessible at this stage.

---

### 4. **Logging Request and Response Bodies with Filter**

#### **Key Highlight: Addition of the Filter**

- [`TransactionFilter.java`](src/main/java/com/apitrial/apitrial/filter/TransactionFilter.java) is a Spring `@Component` filter.
- This filter wraps every HTTP request and response using `ContentCachingRequestWrapper` and `ContentCachingResponseWrapper`.
- **How it works:**
  1. **Before controller:** The filter wraps the request/response and passes them down the filter chain.
  2. **After controller:** The filter reads and prints the full request body and response body to the console.
  3. The response body is copied back to the real response to ensure the client receives it.

- **Why is this important?**
  - The filter allows you to see the exact JSON sent by the client and the exact response sent by your API, which is not possible with just an interceptor.

---

## Project Structure

```
src/main/java/com/apitrial/apitrial/
├── ApitrialApplication.java           # Main Spring Boot application
├── config/
│   └── WebMvcConfig.java              # Registers the LoggingInterceptor
├── controller/
│   └── TransactionController.java     # Handles /pay endpoint
├── filter/
│   └── TransactionFilter.java         # Logs request and response bodies
├── interceptor/
│   └── LoggingInterceptor.java        # Logs request URL/method and response status
└── model/
    └── TransactionRequest.java        # Model for transaction request body
```

---

## Example Flow

1. **Client sends POST request to `/pay`:**
   ```json
   {
     "username": "testuser",
     "amount": 100
   }
   ```

2. **Filter (`TransactionFilter`) logs:**
   ```
   TransactionFilter is active
   Request Body: {"username":"testuser","amount":100}
   Response Body: transaction feasible
   ```

3. **Interceptor (`LoggingInterceptor`) logs:**
   ```
   Intercepted Request Details:
   Request URL: http://localhost:8080/pay
   Request Method: POST
   Response Status: 200
   ----
   ```

4. **Controller (`TransactionController`) processes the request and returns the appropriate response.**

---

## How to Run

1. **Build and start the application:**
   ```bash
   mvn spring-boot:run
   ```

2. **Send a request (example using curl):**
   ```bash
   curl -X POST http://localhost:8080/pay \
     -H "Content-Type: application/json" \
     -d '{"username": "testuser", "amount": 100}'
   ```

3. **Check your console for logs from both the filter and the interceptor.**

---

## Summary

- **Interceptor** logs request metadata and response status.
- **Filter** logs full request and response bodies.
- **Controller** applies business rules and returns a result.

This structure is ideal for debugging, auditing, and understanding the full flow of HTTP requests and responses in a Spring Boot application.

## Features

- **POST /pay** endpoint for processing transactions
- Validates username and amount
- Logs incoming requests using a Spring MVC interceptor
- Clean project structure with separation of concerns

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
