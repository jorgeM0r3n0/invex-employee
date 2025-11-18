package com.invex.jmc.employee.services.impl;

import com.invex.jmc.employee.exceptions.EmployeeNotFoundException;
import com.invex.jmc.employee.exceptions.JobPositionNotFoundException;
import com.invex.jmc.employee.exceptions.SexNotFoundException;
import com.invex.jmc.employee.model.dto.Employee;
import com.invex.jmc.employee.model.dto.request.EmployeeRequest;
import com.invex.jmc.employee.model.dto.request.EmployeesRequest;
import com.invex.jmc.employee.model.entities.EmployeeEntity;
import com.invex.jmc.employee.model.entities.JobPositionEntity;
import com.invex.jmc.employee.model.entities.SexEntity;
import com.invex.jmc.employee.model.mapper.EmployeeMapper;
import com.invex.jmc.employee.model.repositories.EmployeeRepository;
import com.invex.jmc.employee.model.repositories.JobPositionRepository;
import com.invex.jmc.employee.model.repositories.SexRepository;
import com.invex.jmc.employee.services.EmployeeService;
import com.invex.jmc.employee.util.MapperUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link EmployeeService} that provides business logic
 * for managing employee records.
 *
 * <p>This service interacts with repositories, mappers, and utilities to
 * perform CRUD operations and name-based searches. It validates the existence
 * of related entities such as job positions and sex categories before
 * processing requests.</p>
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final JobPositionRepository jobPositionRepository;
  private final SexRepository sexRepository;
  private final MapperUtil mapperUtil;
  private final EmployeeMapper employeeMapper;

  /**
   * Constructs a new instance of {@code EmployeeServiceImpl}.
   *
   * @param employeeRepository the repository used for employee persistence
   * @param jobPositionRepository the repository for job position entities
   * @param sexRepository the repository for sex entities
   * @param mapperUtil utility for object-to-object mapping
   * @param employeeMapper mapper for updating employee entities from request data
   */
  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                             JobPositionRepository jobPositionRepository,
                             SexRepository sexRepository,
                             MapperUtil mapperUtil,
                             EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.jobPositionRepository = jobPositionRepository;
    this.sexRepository = sexRepository;
    this.mapperUtil = mapperUtil;
    this.employeeMapper = employeeMapper;
  }

  /**
   * Retrieves all registered employees.
   *
   * @return a list of {@link Employee} DTOs
   */
  @Override
  public List<Employee> getAllEmployee() {
    return mapperUtil.mapList(employeeRepository.findAll(), Employee.class);
  }

  /**
   * Retrieves an employee by its ID.
   *
   * @param idEmployee the employee identifier
   * @return an {@link Employee} DTO
   * @throws EmployeeNotFoundException if the employee does not exist
   */
  @Override
  public Employee getEmployeeById(String idEmployee) {
    return mapperUtil.map(
      employeeRepository.findEmployeeByIdEmployee(idEmployee)
        .orElseThrow(() -> new EmployeeNotFoundException(idEmployee)),
      Employee.class
    );
  }

  /**
   * Creates multiple employees from the provided request wrapper.
   *
   * <p>Each employee is validated against related entities such as sex and job
   * position. IDs are automatically generated.</p>
   *
   * @param employeesRequest a request object containing a list of employees to create
   * @return a list of created {@link Employee} DTOs
   * @throws SexNotFoundException if the referenced sex ID does not exist
   * @throws JobPositionNotFoundException if the referenced job position ID does not exist
   */
  @Override
  public List<Employee> addEmployees(EmployeesRequest employeesRequest) {
    List<EmployeeRequest> employeeRequestList = employeesRequest.getEmployees();
    List<EmployeeEntity> employeeEntityList = new ArrayList<>();

    for (EmployeeRequest employeeRequest : employeeRequestList) {
      SexEntity sexEntity = sexRepository.findById(employeeRequest.getIdSex())
          .orElseThrow(() -> new SexNotFoundException(employeeRequest.getIdSex()));

      JobPositionEntity jobPositionEntity = jobPositionRepository.findById(
          employeeRequest.getIdJobPosition())
          .orElseThrow(() -> new JobPositionNotFoundException(employeeRequest.getIdJobPosition()));

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      EmployeeEntity employeeEntity = mapperUtil.map(employeeRequest, EmployeeEntity.class);
      employeeEntity.setBirthDay(LocalDate.parse(employeeRequest.getBirthDay(), formatter));
      employeeEntity.setSex(sexEntity);
      employeeEntity.setJobPosition(jobPositionEntity);
      employeeEntity.setIdEmployee(UUID.randomUUID().toString());
      employeeEntity.setTs(LocalDateTime.now());
      employeeEntityList.add(employeeEntity);
    }

    employeeEntityList = employeeRepository.saveAll(employeeEntityList);
    return mapperUtil.mapList(employeeEntityList, Employee.class);
  }

  /**
   * Updates an existing employee using the provided request data.
   *
   * @param idEmployee the ID of the employee to update
   * @param employeeRequest a DTO containing updated employee information
   * @return the updated {@link Employee} DTO
   * @throws EmployeeNotFoundException if no employee exists with the given ID
   * @throws JobPositionNotFoundException if the job position ID does not exist
   * @throws SexNotFoundException if the sex ID does not exist
   */
  @Override
  public Employee updateEmployee(String idEmployee, EmployeeRequest employeeRequest) {
    EmployeeEntity employeeEntity = employeeRepository.findById(idEmployee)
        .orElseThrow(() -> new EmployeeNotFoundException(idEmployee));

    JobPositionEntity jobPositionEntity = jobPositionRepository.findById(
        employeeRequest.getIdJobPosition())
        .orElseThrow(() -> new JobPositionNotFoundException(employeeRequest.getIdJobPosition()));

    SexEntity sexEntity = sexRepository.findById(employeeRequest.getIdSex())
        .orElseThrow(() -> new SexNotFoundException(employeeRequest.getIdSex()));

    employeeMapper.updateEntityFromRequest(employeeEntity, employeeRequest, sexEntity,
        jobPositionEntity);

    employeeRepository.save(employeeEntity);
    return mapperUtil.map(employeeEntity, Employee.class);
  }

  /**
   * Deletes the employee associated with the given ID.
   *
   * @param idEmployee the employee identifier
   * @throws EmployeeNotFoundException if the employee does not exist
   */
  @Override
  public void deleteEmployee(String idEmployee) {
    EmployeeEntity employeeEntity = employeeRepository.findById(idEmployee)
        .orElseThrow(() -> new EmployeeNotFoundException(idEmployee));
    employeeRepository.delete(employeeEntity);
  }

  /**
   * Searches employees whose full name contains the given term.
   *
   * <p>The search is case-insensitive and combines first name, middle name,
   * paternal surname, and maternal surname.</p>
   *
   * @param name the search term
   * @return a list of matching {@link Employee} DTOs
   */
  @Override
  public List<Employee> searchEmployeeByName(String name) {
    return mapperUtil.mapList(employeeRepository.searchByFullName(name), Employee.class);
  }
}
