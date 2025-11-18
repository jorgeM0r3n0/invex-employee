package com.invex.jmc.employee.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.invex.jmc.employee.constants.ApiDescriptionsConstant;
import com.invex.jmc.employee.constants.ValidationMessagesConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


/**
 * Represents a request DTO for creating or updating an employee.
 *
 * <p>This class contains the fields required to register or modify an employee,
 * including personal information, catalog relationships (sex and job position),
 * and birth date formatting.</p>
 *
 * <p>The DTO includes validation constraints using Jakarta Validation and
 * schema annotations for OpenAPI documentation.</p>
 */
@Data
@Schema(
    name = "EmployeeRequest",
    description = "Request object for creating or updating employee information."
)
public class EmployeeRequest {

  /**
   * Unique identifier of the employee.
   *
   * <p>Optional during creation but required for updates. Must contain a UUID string
   * of up to 36 characters.</p>
   */
  @Size(min = ValidationMessagesConstant.MIN_SIZE_36, max = ValidationMessagesConstant.MAX_SIZE_36,
      message =
      ValidationMessagesConstant.ID_EMPLOYEE_SIZE_36)
  @NotNull(message = ValidationMessagesConstant.NOT_NULL)
  @Schema(
      description = ApiDescriptionsConstant.EMPLOYEE_ID_DESC,
      example = ApiDescriptionsConstant.EMPLOYEE_ID_EXAMPLE,
      maxLength = ValidationMessagesConstant.MIN_SIZE_36,
      minLength = ValidationMessagesConstant.MAX_SIZE_36,
      required = true
  )
  private String idEmployee;

  /**
   * First name of the employee.
   * Maximum length: 100 characters.
   */
  @Size(max = 100)
  @Schema(
      description = ApiDescriptionsConstant.FIRST_NAME_DESC,
      example = ApiDescriptionsConstant.FIRST_NAME_EXAMPLE
  )
  private String firstName;

  /**
   * Middle name of the employee.
   * Optional field. Maximum length: 100 characters.
   */
  @Size(max = 100)
  @Schema(
      description = ApiDescriptionsConstant.MIDDLE_NAME_DESC,
      example = ApiDescriptionsConstant.MIDDLE_NAME_EXAMPLE
  )
  private String middleName;

  /**
   * Paternal surname of the employee.
   * Maximum length: 100 characters.
   */
  @Size(max = 100)
  @Schema(
      description = "Employee's paternal surname.",
      example = "Smith"
  )
  private String paternalSurname;

  /**
   * Maternal surname of the employee.
   * Maximum length: 100 characters.
   */
  @Size(max = 100)
  @Schema(
      description = "Employee's maternal surname.",
      example = "Johnson"
  )
  private String maternalSurname;

  /**
   * Identifier of the selected sex in the catalog.
   *
   * <p>Required field. Must reference a valid record in the <code>cat_sex</code> table.</p>
   */
  @NotNull
  @Schema(
      description = "Foreign key referencing the sex catalog.",
      example = "550e8400-e29b-41d4-a716-446655440111",
      required = true
  )
  private String idSex;

  /**
   * Identifier of the job position from the corresponding catalog.
   *
   * <p>Required field.</p>
   */
  @NotNull
  @Schema(
      description = "Foreign key referencing the job position catalog.",
      example = "550e8400-e29b-41d4-a716-446655440222",
      required = true
  )
  private String idJobPosition;

  /**
   * Employee's birth date.
   *
   * <p>Formatted as <code>dd/MM/yyyy</code> when sent via JSON.</p>
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  @Schema(
      description = "Birth date of the employee in dd/MM/yyyy format.",
      example = "25/12/1990"
  )
  private String birthDay;

  /**
   * Status of the employee.
   *
   * <p><code>true</code> indicates active, <code>false</code> inactive.</p>
   */
  @Schema(
      description = "Employee status (true = active, false = inactive).",
      example = "true"
  )
  private int status;
}
