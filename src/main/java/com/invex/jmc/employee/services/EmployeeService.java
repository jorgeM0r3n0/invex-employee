package com.invex.jmc.employee.services;

import com.invex.jmc.employee.model.dto.Employee;
import com.invex.jmc.employee.model.dto.request.EmployeeRequest;
import com.invex.jmc.employee.model.dto.request.EmployeesRequest;
import java.util.List;
import javax.validation.Valid;

/**
 * Service interface that defines business operations related to employees.
 *
 * <p>This interface provides methods for retrieving, creating, updating,
 * deleting, and searching employee records. Implementations of this service
 * should contain the core business logic and interact with repositories and
 * mappers as needed.</p>
 */
public interface EmployeeService {

  /**
   * Retrieves all employees stored in the system.
   *
   * @return a list of all {@link Employee} objects
   */
  List<Employee> getAllEmployee();

  /**
   * Retrieves an employee by its unique identifier.
   *
   * @param idEmployee the ID of the employee to retrieve
   * @return the corresponding {@link Employee}
   * @throws com.invex.jmc.employee.exceptions.EmployeeNotFoundException
   *         if no employee exists with the provided ID
   */
  Employee getEmployeeById(String idEmployee);

  /**
   * Creates one or more employees based on the provided request payload.
   *
   * @param employeesRequest a wrapper object containing a list of employee requests
   * @return a list of {@link Employee} objects representing the created employees
   * @throws javax.validation.ConstraintViolationException
   *         if the request contains invalid data
   */
  List<Employee> addEmployees(@Valid EmployeesRequest employeesRequest);

  /**
   * Updates an existing employee with new information.
   *
   * @param id the ID of the employee to update
   * @param employeeRequest the new data used to update the employee
   * @return the updated {@link Employee}
   * @throws com.invex.jmc.employee.exceptions.EmployeeNotFoundException
   *         if no employee exists with the provided ID
   */
  Employee updateEmployee(String id, @Valid EmployeeRequest employeeRequest);

  /**
   * Deletes an employee identified by its unique ID.
   *
   * @param idEmployee the ID of the employee to delete
   * @throws com.invex.jmc.employee.exceptions.EmployeeNotFoundException
   *         if no employee exists with the provided ID
   */
  void deleteEmployee(String idEmployee);

  /**
   * Searches for employees whose full name matches or contains
   * the specified search term.
   *
   * @param name the search term used to match employee names
   * @return a list of {@link Employee} objects matching the search criteria
   */
  List<Employee> searchEmployeeByName(String name);
}
