package com.invex.jmc.employee.model.dto;

import com.invex.jmc.employee.constants.ApiDescriptionsConstant;
import com.invex.jmc.employee.constants.ValidationMessagesConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Represents a biological sex entry used as a catalog within the system.
 *
 * <p>This class defines the basic attributes of the sex catalog, including:
 * <ul>
 *   <li>A unique identifier (UUID format)</li>
 *   <li>A short code (e.g., M, F)</li>
 *   <li>A descriptive label</li>
 * </ul>
 *
 * <p>The fields include validation constraints using Jakarta Bean Validation
 * and OpenAPI annotations for API documentation.
 */
@Data
@Schema(
    name = "Sex",
    description = "Represents a biological sex catalog entry used within the system."
)
public class Sex {
  /**
   * Unique identifier of the sex.
   *
   * <p>Must be exactly 36 characters long, typically a UUID value.
   *
   * <p>Example: {@code 550e8400-e29b-41d4-a716-446655440000}
   */
  @Size(min = ValidationMessagesConstant.MIN_SIZE_36, max =
      ValidationMessagesConstant.MAX_SIZE_36, message =
      ValidationMessagesConstant.SEX_ID_SIZE_36)
  @NotNull
  @Schema(
      description = ApiDescriptionsConstant.SEX_ID_DESC,
      example = ApiDescriptionsConstant.SEX_ID_EXAMPLE,
      maxLength = ValidationMessagesConstant.MIN_SIZE_36,
      minLength = ValidationMessagesConstant.MAX_SIZE_36,
      required = true
  )
  private String idSex;
  /**
   * Code representing the sex.
   *
   * <p>Typically one character such as 'M' (Male) or 'F' (Female), although the
   * system may allow additional codes based on business rules.
   */
  @NotNull
  @Schema(
      description = "Short code representing the sex. Examples: M = Male, F = Female, I = "
        + "Intersex, U = Unknown.",
      example = "M",
      required = true
  )
  private String code;
  @NotNull
  @Schema(
      description = "Full description of the sex.",
      example = "Male",
      required = true
  )
  private String description;
}
