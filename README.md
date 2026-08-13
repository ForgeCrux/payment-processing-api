# payment-processing-api

Generated Spring Boot application from OpenAPI specification.

## Project Details
- **Group ID**: com.probestack.forgestudio.design
- **Artifact ID**: payment-processing-api
- **Version**: 1.0.0
- **Base Package**: com.probestack.forgestudio.design

## Building the Project
```bash
mvn clean install
```

## Running the Application
```bash
mvn spring-boot:run
```

## API Documentation
Once the application is running, access the Swagger UI at:
- http://localhost:8080/swagger-ui.html

## API Docs (OpenAPI)
- Source spec: `src/main/resources/openapi.yaml`
- Runtime docs: http://localhost:8080/v3/api-docs

## Postman Collection
Import the generated collection from:
- `testing/postman/payment-processing-api.postman_collection.json`

Set the `baseUrl` collection variable to:
- Local: `http://localhost:8080`
- Cloud Run: your deployed service URL

## Default API Behavior
Generated APIs are Mongo-backed scaffolds:
- `POST` operations persist request data and return `201`
- list `GET` operations return persisted records
- `GET /{id}` operations return a persisted record or `404`
- `DELETE /{id}` operations delete persisted records and return `204`
- domain-specific business operations return `501 NOT_IMPLEMENTED` until implemented

## MongoDB Persistence
Generated services use the configured MongoDB connection in `spring.data.mongodb.uri`.
Collections are prefixed by application name to avoid collisions, for example:
- `my_service_accounts`
- `my_service_transactions`

## V2 Enterprise Build Stack

- Java: `21`
- Spring Boot: `3.5.7`
- Maven: `3.9.11`

## API Key Security

This generated service includes API key security because `API_KEY` was selected.

Full documentation:

- [API Key User Guide](docs/security/api_key_user_guide.md)

Default local header:

```http
X-API-Key: change-me-api-key
```


## Request Validation

Generated request validation is configuration-driven.

- Default: validation is disabled for easy scaffold testing.
- Enable strict validation with `APP_VALIDATION_ENABLED=true`.
- Full guide: [Request Validation User Guide](docs/validation/validation_user_guide.md)

When enabled, invalid request bodies return structured `400 validation_error` responses. The generated Postman collection still includes valid sample request bodies even when validation is disabled.


## Exception Handling

This generated service includes optimized exception handling because `frameworkOptions.exceptionHandling=true` was selected.

Full documentation:

- [Exception Handling User Guide](docs/exception/exception_handling_user_guide.md)

### Behavior

- Returns one consistent error response shape for generated API failures.
- Uses one reusable `ApiException` class instead of many generated custom exceptions.
- Includes `correlationId` when available.
- Hides stack traces by default.

### Runtime Configuration

```properties
app.exception.include-system-message=true
app.exception.include-stacktrace=false
app.exception.include-correlation-id=true
```


## Enterprise Logging

This generated service includes configuration-driven HTTP logging because `frameworkOptions.enterpriseLogging=true` was selected.

Full documentation:

- [Logging User Guide](docs/logging/logging_user_guide.md)

### Behavior

- Logs request metadata by default.
- Logs one business operation summary per generated service method by default.
- Can log response metadata, request bodies, response bodies, and headers when enabled by configuration.
- Masks sensitive headers, query parameters, and JSON fields by default.
- Keeps logging outside the security package under `com.probestack.forgestudio.design.logging`.
- Writes structured JSON logs to stdout by default.
- Can write logs to `logs/application.log` for local Grafana/Loki or Splunk-forwarder testing.

### Runtime Configuration

All logging behavior is controlled with `app.logging.*` properties or matching environment variables. The generated defaults are:

```properties
app.logging.provider=CONSOLE
app.logging.destination=CONSOLE
```

Disable all generated HTTP logging with:

```properties
app.logging.enabled=false
```


## Cloud Run Deployment
This generated project includes GitHub Actions CI/CD for Google Cloud Run.

- Workflow: `.github/workflows/ci-cd.yml`
- Service name: `payment-processing-api`
- GCP project: `probestack-prod`
- Region: `us-central1`
- Artifact Registry repository: `us-central1-docker.pkg.dev/probestack-prod/probestack-prod-apps`

On every GitHub push, the workflow builds the application, publishes a Docker image, deploys to Cloud Run, and verifies `/actuator/health`.

See `DEPLOYMENT.md` for setup instructions.
