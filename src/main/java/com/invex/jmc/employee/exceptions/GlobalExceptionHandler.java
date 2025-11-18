package com.invex.jmc.employee.exceptions;

import com.invex.jmc.employee.model.dto.response.ErrorResponse;
import java.time.LocalDateTime;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler centralizes the exception handling logic for the application.
 *
 * <p>This class uses Spring's {@link org.springframework.web.bind.annotation.ControllerAdvice}
 * mechanism to intercept exceptions thrown by controllers and return structured,
 * meaningful error responses to the client.
 *
 * <p>Each specific exception type is mapped to the appropriate HTTP status code and an
 * {@link ErrorResponse} object describing the error.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles {@link EmployeeNotFoundException} exceptions.
   *
   * <p>Triggered when an employee is requested by ID but does not exist in the system.
   *
   * @param ex the exception thrown when an employee is not found
   * @return a {@link ResponseEntity} containing an {@link ErrorResponse} with HTTP 404 status
   */
  @ExceptionHandler(EmployeeDuplicateException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(EmployeeDuplicateExceptionWithField ex) {
    ErrorResponse error = new ErrorResponse();
    error.setMensaje("Employee not found");
    error.setCampo(ex.getField());
    error.setDetalle(ex.getMessage());
    error.setTimestamp(LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  /**
   * Handles {@link EmployeeNotFoundException} exceptions.
   *
   * <p>Triggered when an employee is requested by ID but does not exist in the system.
   *
   * @param ex the exception thrown when an employee is not found
   * @return a {@link ResponseEntity} containing an {@link ErrorResponse} with HTTP 404 status
   */
  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(EmployeeNotFoundException ex) {
    ErrorResponse error = new ErrorResponse();
    error.setMensaje("Employee not found");
    error.setCampo("idEmployee");
    error.setDetalle(ex.getMessage());
    error.setTimestamp(LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  /**
   * Handles {@link JobPositionNotFoundException} exceptions.
   *
   * <p>Occurs when an employee is assigned a job position that does not exist.
   *
   * @param ex the exception thrown when a job position is not found
   * @return a {@link ResponseEntity} with HTTP 404 and detailed error information
   */
  @ExceptionHandler(JobPositionNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(JobPositionNotFoundException ex) {
    ErrorResponse error = new ErrorResponse();
    error.setCampo("idJobPosition");
    error.setMensaje("Job Position not found");
    error.setDetalle(ex.getMessage());
    error.setTimestamp(LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  /**
   * Handles {@link ChangeSetPersister.NotFoundException}, typically thrown by Spring Data.
   *
   * @param ex the exception thrown when an entity is not found
   * @return a simple plain-text response with HTTP 404 status
   */
  @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
  public ResponseEntity<String> handleNotFound(ChangeSetPersister.NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  /**
   * Handles {@link SexNotFoundException} exceptions.
   *
   * <p>This exception is thrown when the provided sex identifier does not match any record.
   *
   * @param ex the thrown exception
   * @return a {@link ResponseEntity} with HTTP status 404 and a formatted error response
   */
  @ExceptionHandler(SexNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(SexNotFoundException ex) {
    ErrorResponse error = new ErrorResponse();
    error.setCampo("idSex");
    error.setMensaje("Sex not found");
    error.setDetalle(ex.getMessage());
    error.setTimestamp(LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  /**
   * Handles any uncaught or generic {@link Exception}.
   *
   * <p>This acts as a safety net for unexpected errors that are not explicitly handled
   * elsewhere in the application.
   *
   * @param ex the generic exception thrown at runtime
   * @return a {@link ResponseEntity} with HTTP 500 and a general error description
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ErrorResponse error = new ErrorResponse();
    error.setCampo("");
    error.setMensaje("Error interno" + HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setDetalle(ex.getMessage());
    error.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handles {@link IllegalArgumentException} for invalid request parameters.
   *
   * @param ex the thrown exception
   * @return a {@link ResponseEntity} with HTTP 400 and the exception message
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}
