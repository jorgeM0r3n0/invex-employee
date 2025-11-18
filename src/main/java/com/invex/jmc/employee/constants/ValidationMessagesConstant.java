package com.invex.jmc.employee.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Contains constant values used for validation messages across the application.
 *
 * <p>This utility class centralizes all validation messages to ensure consistency
 * and avoid duplication in annotations and exception handling.
 * </p>
 *
 * <p><strong>Note:</strong> This class cannot be instantiated.</p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationMessagesConstant {

  // Generic messages
  public static final String NOT_NULL = "This field cannot be null.";
  public static final String NOT_EMPTY = "This field cannot be empty.";
  public static final String INVALID_FORMAT = "Invalid format.";
  public static final String INVALID_UUID = "The value must be a valid UUID.";

  // EmployeeRequest
  public static final int MAX_SIZE_36 = 36;
  public static final int MAX_SIZE_3 = 36;
  public static final int MIN_SIZE_36 = 36;
  public static final int MIN_SIZE_1 = 36;
  public static final int MAX_SIZE_100 = 100;
  public static final String ID_EMPLOYEE_SIZE_36 =
      "The employee ID must contain exactly 36 characters (UUID).";
  public static final String ID_EMPLOYEE_NOT_NULL =
      "The employee ID cannot be null.";
  public static final String FIRST_NAME_MAX_100 =
      "The first name must not exceed 100 characters.";
  public static final String MIDDLE_NAME_MAX_100 =
      "The middle name must not exceed 100 characters.";
  public static final String PATERNAL_SURNAME_MAX_100 =
      "The paternal surname must not exceed 100 characters.";
  public static final String MATERNAL_SURNAME_MAX_100 =
      "The maternal surname must not exceed 100 characters.";
  public static final String ID_SEX_NOT_NULL =
      "The sex identifier must not be null.";
  public static final String ID_JOB_POSITION_NOT_NULL =
      "The job position identifier must not be null.";

  // Sex
  public static final String SEX_ID_SIZE_36 =
      "Sex identifier must contain exactly 36 characters.";
  public static final String SEX_CODE_NOT_NULL =
      "Sex code cannot be null.";
  public static final String SEX_DESCRIPTION_NOT_NULL =
      "Sex description cannot be null.";

  // JobPosition
  public static final String JOB_POSITION_ID_SIZE_36 =
      "Job position ID must contain exactly 36 characters.";
  public static final String JOB_POSITION_CODE_MAX_3 =
      "Job position code must not exceed 3 characters.";
  public static final String JOB_POSITION_DESCRIPTION_NOT_NULL =
      "Job position description cannot be null.";

  // List wrapper (EmployeesRequest)
  public static final String EMPLOYEES_LIST_NOT_NULL =
      "The employees list cannot be null.";
  public static final String EMPLOYEES_LIST_INVALID =
      "The employees list contains invalid items.";

  // ErrorResponse
  public static final String ERROR_DETAIL_NOT_NULL =
      "The error detail cannot be null.";
}
