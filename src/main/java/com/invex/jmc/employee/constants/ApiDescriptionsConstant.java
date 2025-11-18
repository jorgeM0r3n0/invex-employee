package com.invex.jmc.employee.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Centralized constants for OpenAPI descriptions and examples.
 *
 * <p>This class contains reusable text fragments used across @Schema annotations
 * to avoid duplication and simplify maintenance.</p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiDescriptionsConstant {
  public static final String EMPLOYEE_ID_DESC =
      "Unique identifier of the employee (UUID). Optional when creating.";
  public static final String EMPLOYEE_ID_EXAMPLE =
      "550e8400-e29b-41d4-a716-446655440000";

  public static final String FIRST_NAME_DESC = "Employee's first name.";
  public static final String FIRST_NAME_EXAMPLE = "John";

  public static final String MIDDLE_NAME_DESC = "Employee's middle name (optional).";
  public static final String MIDDLE_NAME_EXAMPLE = "Alexander";

  public static final String PATERNAL_SURNAME_DESC = "Employee's paternal surname.";
  public static final String PATERNAL_SURNAME_EXAMPLE = "Smith";

  public static final String MATERNAL_SURNAME_DESC = "Employee's maternal surname.";
  public static final String MATERNAL_SURNAME_EXAMPLE = "Johnson";

  public static final String ID_SEX_DESC = "Foreign key referencing the sex catalog.";
  public static final String ID_SEX_EXAMPLE =
      "550e8400-e29b-41d4-a716-446655440111";

  public static final String ID_JOB_POSITION_DESC =
      "Foreign key referencing the job position catalog.";
  public static final String ID_JOB_POSITION_EXAMPLE =
      "550e8400-e29b-41d4-a716-446655440222";

  public static final String BIRTHDAY_DESC =
      "Birth date of the employee in dd/MM/yyyy format.";
  public static final String BIRTHDAY_EXAMPLE = "25/12/1990";

  public static final String STATUS_DESC =
      "Employee status (true = active, false = inactive).";
  public static final String STATUS_EXAMPLE = "true";


  // EmployeesRequest
  public static final String EMPLOYEES_LIST_DESC =
      "List of employees to be created. Each entry must be a valid EmployeeRequest.";


  // Sex
  public static final String SEX_ID_DESC = "Unique identifier of the sex (UUID).";
  public static final String SEX_ID_EXAMPLE =
      "550e8400-e29b-41d4-a716-446655440000";

  public static final String SEX_CODE_DESC =
      "Short code representing the sex. Examples: M = Male, F = Female, I = Intersex, U = Unknown.";
  public static final String SEX_CODE_EXAMPLE = "M";

  public static final String SEX_DESC_DESC = "Full description of the sex.";
  public static final String SEX_DESC_EXAMPLE = "Male";


  // ErrorResponse
  public static final String ERROR_DETAIL_DESC =
      "Technical detail of the error.";
  public static final String ERROR_DETAIL_EXAMPLE =
      "NullPointerException: employee name was null";

  public static final String ERROR_MESSAGE_DESC =
      "Human-readable message describing the error.";
  public static final String ERROR_MESSAGE_EXAMPLE =
      "The field 'firstName' cannot be null.";

  public static final String ERROR_FIELD_DESC =
      "Name of the field that caused the error, if applicable.";
  public static final String ERROR_FIELD_EXAMPLE = "firstName";

  public static final String ERROR_TIMESTAMP_DESC =
      "Date and time when the error occurred.";
  public static final String ERROR_TIMESTAMP_EXAMPLE = "2025-11-13T10:15:42";
}
