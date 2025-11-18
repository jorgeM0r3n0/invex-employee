package com.invex.jmc.employee.exceptions;

/**
 * Exception thrown when attempting to create or update an employee
 * with a field value that must be unique but is already in use.
 *
 * <p>This exception extends {@link EmployeeDuplicateException} and adds
 * the specific field that caused the conflict, making it easier to identify
 * validation issues at runtime.</p>
 *
 * @author Jorge
 * @see EmployeeDuplicateException
 */
public class EmployeeDuplicateExceptionWithField extends EmployeeDuplicateException {

  /**
   * The name of the field that has a duplicate value.
   */
  private final String field;

  /**
   * Creates a new exception indicating which field caused a duplicate conflict.
   *
   * @param campo   the name of the field that contains a duplicate value
   * @param mensaje the detail message explaining the cause of the error
   */
  public EmployeeDuplicateExceptionWithField(String campo, String mensaje) {
    super(mensaje);
    this.field = campo;
  }

  /**
   * Returns the name of the field that has a duplicate value.
   *
   * @return the field name that triggered the duplicate exception
   */
  public String getField() {
    return field;
  }
}
