package com.invex.jmc.employee.exceptions;

/**
 * Exception thrown when an employee with the specified identifier
 * cannot be found in the system.
 *
 * <p>This exception is typically raised by service-layer operations
 * when a lookup is performed using a non-existent employee ID.</p>
 */
public class EmployeeNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code EmployeeNotFoundException} with a message
   * indicating the employee ID that could not be resolved.
   *
   * @param idEmployee the employee identifier that was not found
   */
  public EmployeeNotFoundException(String idEmployee) {
    super("Employee not found for id : " + idEmployee);
  }
}