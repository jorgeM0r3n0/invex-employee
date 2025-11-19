package com.invex.jmc.employee.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class that centralizes constant values used for logging purposes.
 *
 * <p>This class is declared with a private no-args constructor
 * (via {@code @NoArgsConstructor(access = AccessLevel.PRIVATE)})
 * to prevent instantiation, as it serves exclusively as a container
 * for static logging-related constants.</p>
 *
 * <p>These constants are typically used with logging frameworks such as
 * SLF4J to standardize log message formats across the application,
 * ensuring consistency and reducing duplication.</p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerConstantsUtil {

  /**
   * Log template used to print the headers received for the
   * <strong>Invex-Employee</strong> API.
   *
   * <p>The placeholder {@code {}} is intended to be used with SLF4J's
   * parameterized logging syntax, allowing header values to be injected
   * dynamically at runtime.</p>
   *
   * <p>Example usage:</p>
   *
   * <pre>{@code
   * log.debug(LoggerConstantsUtil.EMPLOYEE_PERFORMANCE_HEADERS, headers);
   * }</pre>
   */
  public static final String EMPLOYEE_PERFORMANCE_HEADERS = "\nInvex-Employee Headers : \n{}";
}
