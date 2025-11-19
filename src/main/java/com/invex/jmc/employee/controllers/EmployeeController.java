package com.invex.jmc.employee.controllers;

import com.invex.jmc.employee.config.headers.HeaderConstraint;
import com.invex.jmc.employee.constants.ConstantsUtil;
import com.invex.jmc.employee.constants.LoggerConstantsUtil;
import com.invex.jmc.employee.model.dto.Employee;
import com.invex.jmc.employee.model.dto.request.EmployeeRequest;
import com.invex.jmc.employee.model.dto.request.EmployeesRequest;
import com.invex.jmc.employee.services.EmployeeService;
import com.invex.jmc.employee.util.LoggerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes operations related to employees.
 *
 * <p>This controller handles CRUD operations such as listing, retrieving,
 * creating, updating, and deleting employees, as well as searching employees
 * by name.</p>
 */
@Slf4j
@RestController
@Validated
@RequestMapping("/api/employees")
@Tag(name = "Employees", description = "Operations related to employees")
public class EmployeeController {
  private final EmployeeService employeeService;

  /**
   * Constructor method.
   *
   * @param employeeService The eployee service.
   */
  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  /**
   * Retrieves all employees.
   *
   * @return a list of all registered employees
   */
  @GetMapping()
  @Operation(
      summary = "List all employees",
      description = "Returns the complete list of employees",
      responses = {
        @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      }
  )
  public ResponseEntity<List<Employee>> getAllEmployees(@RequestHeader @HeaderConstraint(api =
      ConstantsUtil.HEADERS_CONSTRAINT
  ) HttpHeaders headers) {
    LoggerUtils.logInfoJson(log, LoggerConstantsUtil.EMPLOYEE_PERFORMANCE_HEADERS, headers);
    return ResponseEntity.ok().body(employeeService.getAllEmployee());
  }

  /**
   * Retrieves a specific employee by its ID.
   *
   * @param id the employee identifier
   * @return the employee details
   */
  @GetMapping("/{id}")
  @Operation(
      summary = "Get employee by ID",
      description = "Fetches employee details for the given ID",
      responses = {
        @ApiResponse(responseCode = "200", description = "Employee found"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
      }
  )
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id,
                                                  @RequestHeader @HeaderConstraint(api =
      ConstantsUtil.HEADERS_CONSTRAINT
  ) HttpHeaders headers) {
    LoggerUtils.logInfoJson(log, LoggerConstantsUtil.EMPLOYEE_PERFORMANCE_HEADERS, headers);
    return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
  }

  /**
   * Inserts one or more employees.
   *
   * @param employeesRequest the request containing a list of employees
   * @return the created employees
   */
  @PostMapping()
  @Operation(
      summary = "Crear empleado",
      description = "Inserta uno o varios empleados"
  )
  public ResponseEntity<List<Employee>> addEmployees(@Valid @RequestBody
                                                      EmployeesRequest employeesRequest,
                                                     @RequestHeader @HeaderConstraint(api =
      ConstantsUtil.HEADERS_CONSTRAINT
  ) HttpHeaders headers) {
    LoggerUtils.logInfoJson(log, LoggerConstantsUtil.EMPLOYEE_PERFORMANCE_HEADERS, headers);
    return ResponseEntity.status(HttpStatus.CREATED).body(
      employeeService.addEmployees(employeesRequest));
  }

  /**
   * Updates an existing employee.
   *
   * @param id the employee identifier
   * @param employeeRequest the updated data for the employee
   * @return the updated employee
   */
  @PutMapping("/{id}")
  @Operation(
      summary = "Update employee",
      description = "Updates an existing employee identified by its ID"
  )
  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String id,
      @Valid @RequestBody EmployeeRequest employeeRequest, @RequestHeader @HeaderConstraint(api =
      ConstantsUtil.HEADERS_CONSTRAINT
    ) HttpHeaders headers) {
    LoggerUtils.logInfoJson(log, LoggerConstantsUtil.EMPLOYEE_PERFORMANCE_HEADERS, headers);
    return ResponseEntity.ok().body(employeeService.updateEmployee(id, employeeRequest));
  }

  /**
   * Deletes an employee by ID.
   *
   * @param id the employee identifier
   */
  @DeleteMapping("/{id}")
  @Operation(
      summary = "Delete employee",
      description = "Deletes an employee by its ID"
  )
  public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String id,
                                             @RequestHeader @HeaderConstraint(api =
      ConstantsUtil.HEADERS_CONSTRAINT
  ) HttpHeaders headers) {
    LoggerUtils.logInfoJson(log, LoggerConstantsUtil.EMPLOYEE_PERFORMANCE_HEADERS, headers);
    employeeService.deleteEmployee(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Searches employees by full or partial name.
   *
   * @param name the name filter
   * @return a list of employees whose name matches the query
   */
  @GetMapping("/search")
  @Operation(
      summary = "Search employees by name",
      description = "Returns employees whose full name contains the given text"
  )
  public ResponseEntity<List<Employee>> searchEmployeeByName(
      @RequestParam(required = true) String name, @RequestHeader @HeaderConstraint(api =
      ConstantsUtil.HEADERS_CONSTRAINT
    ) HttpHeaders headers) {
    LoggerUtils.logInfoJson(log, LoggerConstantsUtil.EMPLOYEE_PERFORMANCE_HEADERS, headers);
    return ResponseEntity.ok(employeeService.searchEmployeeByName(name));
  }
}
