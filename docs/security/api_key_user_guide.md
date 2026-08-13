# API Key Security User Guide

This service was generated with API key security because `securityOptions.type=API_KEY` was selected.

## What Is API Key Security?

API key security is a simple shared-secret authentication model. The caller sends a known key in an HTTP header, and the service compares that value with the configured server-side value.

It is easy to use for local testing and simple internal integrations. For higher assurance service-to-service request integrity, use HMAC. For token-based identity and authorization, use JWT or OAuth2.

## Generated Behavior

- Health and OpenAPI documentation endpoints are public.
- Generated business APIs require the configured API key header by default.
- Missing or invalid keys return `401`.
- The API key value is configured through properties or environment variables.

Public endpoints:

```text
/actuator/health/**
/swagger-ui/**
/swagger-ui.html
/v3/api-docs/**
```

## Configuration

```properties
app.security.api-key.enabled=${APP_SECURITY_API_KEY_ENABLED:true}
app.security.api-key.header-name=${APP_SECURITY_API_KEY_HEADER_NAME:X-API-Key}
app.security.api-key.value=${APP_SECURITY_API_KEY_VALUE:change-me-api-key}
app.security.api-key.protect-api-endpoints=${APP_SECURITY_API_KEY_PROTECT_API_ENDPOINTS:true}
```

## Test Locally

Start the service:

```bash
mvn spring-boot:run
```

Call a protected API:

```bash
curl http://localhost:8080/your-api-path \
  -H "X-API-Key: change-me-api-key" \
  -H "Accept: application/json"
```

Missing or invalid key:

```json
{
  "code": "invalid_api_key",
  "userMessage": "API key is missing or invalid",
  "systemMessage": "Missing or invalid X-API-Key header"
}
```

## Production Recommendations

- Replace the default key before deployment.
- Use an environment variable or secret manager for `APP_SECURITY_API_KEY_VALUE`.
- Rotate API keys periodically.
- Do not commit production API keys to source control.
