package com.probestack.forgestudio.design.security.apikey;

import com.probestack.forgestudio.design.config.security.ApiKeySecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Protects generated APIs with a configured API key header.
 */
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiKeyAuthenticationFilter.class);

    private final ApiKeySecurityProperties properties;
    private final ObjectMapper objectMapper;

    public ApiKeyAuthenticationFilter(ApiKeySecurityProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    /**
     * Permits public platform endpoints and validates API key headers for business APIs.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String path = normalizedPath(request);
        if (!properties.isEnabled() || !properties.isProtectApiEndpoints() || isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String providedKey = request.getHeader(properties.getHeaderName());
        if (!matches(providedKey, properties.getValue())) {
            log.warn("API key request rejected. eventType=SECURITY_REQUEST_REJECTED, code={}, status={}, method={}, path={}, reason={}",
                    "invalid_api_key",
                    HttpServletResponse.SC_UNAUTHORIZED,
                    request.getMethod(),
                    request.getRequestURI(),
                    "Missing or invalid API key header");
            writeJson(response, HttpServletResponse.SC_UNAUTHORIZED, securityError(
                    "invalid_api_key",
                    "API key is missing or invalid",
                    "Missing or invalid " + properties.getHeaderName() + " header"
            ));
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                "api-key-client",
                null,
                List.of(new SimpleGrantedAuthority("ROLE_API_CLIENT"))
        ));
        try {
            filterChain.doFilter(request, response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private boolean matches(String provided, String expected) {
        if (provided == null || expected == null) {
            return false;
        }
        return MessageDigest.isEqual(
                provided.getBytes(StandardCharsets.UTF_8),
                expected.getBytes(StandardCharsets.UTF_8)
        );
    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/actuator/health")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs");
    }

    private String normalizedPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String context = request.getContextPath();
        return context == null || context.isBlank() ? uri : uri.substring(context.length());
    }

    private Map<String, Object> securityError(String code, String userMessage, String systemMessage) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", code);
        body.put("userMessage", userMessage);
        body.put("systemMessage", systemMessage);
        return body;
    }

    private void writeJson(HttpServletResponse response, int status, Map<String, Object> body) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
