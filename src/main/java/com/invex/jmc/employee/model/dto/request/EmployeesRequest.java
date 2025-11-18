package com.invex.jmc.employee.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;


/**
 * Represents a wrapper request containing a list of employees.
 *
 * <p>This class is used when the API allows the creation of multiple employees
 * in a single request (bulk insert). Each item in the list must be a valid
 * {@link EmployeeRequest} object.</p>
 *
 * <p>The list is validated with {@code @Valid} to ensure that all nested
 * EmployeeRequest objects comply with validation constraints.</p>
 */
@Data
@Schema(
    name = "EmployeesRequest",
    description = "Request object for bulk creation of employees. Contains a list of "
      + "EmployeeRequest items."
)
public class EmployeesRequest {

  /**
   * List of employees to be processed.
   *
   * <p>This field is required and must contain at least one valid EmployeeRequest.</p>
   */
  @NotNull
  @Valid
  @Schema(
      description = "List of employees to be created. Each entry must be a valid EmployeeRequest.",
      required = true
  )
  private List<EmployeeRequest> employees;
}

