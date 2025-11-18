package com.invex.jmc.employee.exceptions;

/**
 * Exception thrown when a Sex entity cannot be found by its identifier.
 *
 * <p>This exception is typically raised when an operation requires a valid
 * sex reference (e.g., during employee creation or update), but the provided
 * {@code idSex} does not exist in the database.</p>
 */
public class SexNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code SexNotFoundException} with a detailed message
   * including the missing sex identifier.
   *
   * @param idSex the identifier of the sex that was not found
   */
  public SexNotFoundException(String idSex) {
    super("Sex not found for id : " + idSex);
  }
}
