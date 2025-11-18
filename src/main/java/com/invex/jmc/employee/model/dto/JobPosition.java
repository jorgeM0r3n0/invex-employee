package com.invex.jmc.employee.model.dto;

import com.invex.jmc.employee.constants.ApiDescriptionsConstant;
import com.invex.jmc.employee.constants.ValidationMessagesConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Represents a job position within the organization.
 *
 * <p>This class contains the essential information of a job position, including:
 * <ul>
 *   <li>A unique identifier (UUID format)</li>
 *   <li>A short alphanumeric code</li>
 *   <li>A human-readable description</li>
 * </ul>
 *
 * <p>The fields are validated using Jakarta Bean Validation constraints and
 * documented using OpenAPI annotations for API specification.
 */
@Data
public class JobPosition {
  @Size(min = ValidationMessagesConstant.MIN_SIZE_36, max = 36, message =
      ValidationMessagesConstant.JOB_POSITION_ID_SIZE_36)
  @NotNull(message = ValidationMessagesConstant.ID_EMPLOYEE_NOT_NULL)
  @Schema(
      description = ApiDescriptionsConstant.ID_JOB_POSITION_DESC,
      example = ApiDescriptionsConstant.ID_JOB_POSITION_EXAMPLE,
      maxLength = ValidationMessagesConstant.MIN_SIZE_36,
      minLength = ValidationMessagesConstant.MAX_SIZE_36,
      required = true
  )
  private String idJobPosition;

  /**
   * Short code representing the job position.
   *
   * <p>This code is typically an acronym or short identifier with a maximum
   * length of 3 characters.
   *
   * <p>Example: {@code CEO}
   */
  @NotNull(message = ValidationMessagesConstant.SEX_CODE_NOT_NULL)
  @Size(min = ValidationMessagesConstant.MIN_SIZE_1, max = ValidationMessagesConstant.MAX_SIZE_3)
  @Schema(description = "Code for job position",
      example = "CEO",
      maxLength = ValidationMessagesConstant.MAX_SIZE_3,
      minLength = ValidationMessagesConstant.MIN_SIZE_1,
      required = true)
  private String code;

  /**
   * Full textual description of the job position.
   *
   * <p>This typically includes the full role name or title.
   *
   * <p>Example: {@code Chief Executive Officer}
   */
  @NotNull(message = ValidationMessagesConstant.JOB_POSITION_DESCRIPTION_NOT_NULL)
  @Size(min = ValidationMessagesConstant.MIN_SIZE_1, max = ValidationMessagesConstant.MAX_SIZE_100)
  @Schema(description = "Description for job position",
      example = "Chief Executive Officer",
      maxLength = ValidationMessagesConstant.MAX_SIZE_100,
      minLength = ValidationMessagesConstant.MIN_SIZE_1,
      required = true)
  private String description;
}
