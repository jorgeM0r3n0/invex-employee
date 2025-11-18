package com.invex.jmc.employee.exceptions;

/**
 * Exception thrown to indicate that an attempt was made to create or register
 * an employee that already exists in the system.
 *
 * <p>This exception is typically used in service or business logic layers to
 * enforce uniqueness constraints based on attributes such as employee ID,
 * email, or other unique fields.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * if (employeeRepository.existsByEmail(request.getEmail())) {
 *     throw new EmployeeDuplicateException(
 *         "An employee with email " + request.getEmail() + " already exists."
 *     );
 * }
 * }</pre>
 *
 * @see RuntimeException
 * @author Jorge
 */
public class EmployeeDuplicateException  extends RuntimeException {
  /**
   * Creates a new {@code EmployeeDuplicateException} with the specified detail message.
   *
   * @param message the detail message explaining the cause of the exception
   */
  public EmployeeDuplicateException(String message) {
    super(message);
  }
}
