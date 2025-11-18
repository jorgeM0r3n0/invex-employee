package com.invex.jmc.employee.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;


/**
 * Represents a standardized error response returned by the API.
 *
 * <p>This class encapsulates detailed information about an error that occurred
 * during the execution of a request, including a human-readable message,
 * the field associated with the error (if applicable), and a timestamp
 * indicating when the error was generated.</p>
 *
 * <p>It is used consistently across exception handlers to provide
 * uniform error response structures to clients.</p>
 */
@Data
@Schema(
    name = "ErrorResponse",
    description = "Standard error response structure returned by the API."
)
public class ErrorResponse {

  /**
   * Technical detail of the error.
   *
   * <p>This typically contains the underlying cause or internal explanation,
   * useful for debugging.</p>
   */
  @Schema(
      description = "Technical detail of the error.",
      example = "NullPointerException: employee name was null"
  )
  private String detalle;

  /**
   * Human-readable error message intended for the client.
   */
  @Schema(
      description = "Human-readable message describing the error.",
      example = "The field 'firstName' cannot be null."
  )
  private String mensaje;

  /**
   * Field associated with the error, when applicable.
   *
   * <p>If the error is not related to a specific field, this may be null.</p>
   */
  @Schema(
      description = "Name of the field that caused the error, if applicable.",
      example = "firstName"
  )
  private String campo;

  /**
   * Timestamp indicating when the error occurred.
   *
   * <p>The timestamp uses server local time formatting.</p>
   */
  @Schema(
      description = "Date and time when the error occurred.",
      example = "2025-11-13T10:15:42"
  )
  private LocalDateTime timestamp;
}
