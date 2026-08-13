package com.probestack.forgestudio.design.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;
import jakarta.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * RefundRequest
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-08-13T10:12:31.837361043Z[GMT]")
@Document(collection = "payment_processing_api_default")
public class RefundRequest {

    private BigDecimal amount;

    private String reason;

    public RefundRequest() {
        super();
    }

    /**
     * Constructor with only required parameters
     */
    public RefundRequest(BigDecimal amount) {
        this.amount = amount;
    }

    public RefundRequest amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Get amount
     * @return amount
     */
    @NotNull
    @Valid
    @Schema(name = "amount", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public RefundRequest reason(String reason) {
        this.reason = reason;
        return this;
    }

    /**
     * Get reason
     * @return reason
     */
    @Schema(name = "reason", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RefundRequest refundRequest = (RefundRequest) o;
        return Objects.equals(this.amount, refundRequest.amount) && Objects.equals(this.reason, refundRequest.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, reason);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RefundRequest {\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @Id()
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
