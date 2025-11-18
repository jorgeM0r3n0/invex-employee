package com.invex.jmc.employee.exceptions;

/**
 * Exception thrown when a Job Position cannot be found using the provided identifier.
 *
 * <p>This runtime exception is typically thrown from the service layer when a
 * lookup in the persistence layer does not return any result for the specified
 * job position ID.</p>
 *
 * <p>The exception message includes the ID used for the search to aid in
 * debugging and error tracking.</p>
 */
public class JobPositionNotFoundException extends RuntimeException {
  /**
   * Creates a new {@code JobPositionNotFoundException} with a detailed message
   * including the job position identifier that could not be found.
   *
   * @param idJobPosition the identifier of the job position that was not located
   */
  public JobPositionNotFoundException(String idJobPosition) {
    super("Job Position not found for id : " + idJobPosition);
  }
}