package com.probestack.forgestudio.design.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for generated API key security.
 */
@ConfigurationProperties(prefix = "app.security.api-key")
public class ApiKeySecurityProperties {

    private boolean enabled = true;
    private String headerName = "X-API-Key";
    private String value = "change-me-api-key";
    private boolean protectApiEndpoints = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isProtectApiEndpoints() {
        return protectApiEndpoints;
    }

    public void setProtectApiEndpoints(boolean protectApiEndpoints) {
        this.protectApiEndpoints = protectApiEndpoints;
    }
}
