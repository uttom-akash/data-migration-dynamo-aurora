package com.bkash.savings.models.dto.fi.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * This is copied from the previous codebase.
 * <p>Related to v1 organization request.</p>
 * <p>
 *     Please note that, this class also has usage in v2 type organization for some apis
 * </p>
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RequestBody implements Serializable {
    private String requestId;
    private String correlationId;
    private Long timestamp;
    @NotBlank(message = "Organization code cannot be empty")
    private String orgCode;
    private Object referenceParams;
}
