package com.invex.jmc.employee.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.invex.jmc.employee.constants.ConstantsUtil;
import com.invex.jmc.employee.model.dto.Employee;
import com.invex.jmc.employee.model.dto.request.EmployeeRequest;
import com.invex.jmc.employee.model.dto.request.EmployeesRequest;
import com.invex.jmc.employee.model.entities.EmployeeEntity;
import com.invex.jmc.employee.model.repositories.EmployeeRepository;
import com.invex.jmc.employee.model.repositories.JobPositionRepository;
import com.invex.jmc.employee.model.repositories.SexRepository;
import com.invex.jmc.employee.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("/invexPropertiesTest.properties")
@ActiveProfiles("test")
class EmployeeControllerTest {

  @MockBean
  private EmployeeService employeeService;

  @Autowired
  private EmployeeController employeeController;

  @Value("${employees.list}")
  String employeesJson;
  @Value("${employee}")
  String employeeJson;
  @Value("${employeesRequest}")
  String employeesRequestJson;

  @Value("${idEmployee}")
  String idEmployee;

  private List<Employee> employeeList;
  private EmployeesRequest employeesRequest;
  private EmployeeRequest employeeRequest;
  private Employee employee;
  HttpHeaders httpHeaders;

  @BeforeEach
  void setUp() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    try {
      employeeList = mapper.readValue(
        employeesJson,
        new TypeReference<List<Employee>>() {}
      );
      employeesRequest = mapper.readValue(
        employeesRequestJson,
        new TypeReference<EmployeesRequest>() {}
      );
      employee = mapper.readValue(
        employeeJson,
        new TypeReference<Employee>() {}
      );
      httpHeaders = new HttpHeaders();
      httpHeaders.add(ConstantsUtil.APPLICATION_JSON, "json");
      httpHeaders.add(ConstantsUtil.ACCEPT, "*/*");
    }
    catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getAllEmployees() {
    when(employeeService.getAllEmployee()).thenReturn(employeeList);
    ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees(httpHeaders);
    assertNotNull(responseEntity.getBody());
    assertEquals(employeeList.size(), responseEntity.getBody().size());
  }

  @Test
  void getEmployeeById() {
    when(employeeService.getEmployeeById(idEmployee)).thenReturn(employee);
    ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById(idEmployee,httpHeaders);
    assertNotNull(responseEntity.getBody());
    assertEquals(idEmployee, responseEntity.getBody().getIdEmployee());
  }

  @Test
  void addEmployees() {
    when(employeeService.addEmployees(employeesRequest)).thenReturn(employeeList);
    ResponseEntity<List<Employee>> responseEntity =
      employeeController.addEmployees(employeesRequest,httpHeaders);
    assertNotNull(responseEntity.getBody());
    assertEquals(employeeList.size(), responseEntity.getBody().size());
  }

  @Test
  void updateEmployee() {
    when(employeeService.updateEmployee(idEmployee,employeeRequest)).thenReturn(employeeList.get(0));
    ResponseEntity<Employee> responseEntity = employeeController.updateEmployee(idEmployee,
      employeeRequest,httpHeaders);
    assertNotNull(responseEntity.getBody());
  }

  @Test
  void deleteEmployee() {
    doNothing().when(employeeService).deleteEmployee(idEmployee);
    ResponseEntity<Void> responseEntity = employeeController.deleteEmployee(idEmployee,httpHeaders);
    assertNotNull(responseEntity);
  }

  @Test
  void searchEmployeeByName() {
    String txt = "ore";
    when(employeeService.searchEmployeeByName(txt)).thenReturn(employeeList);
    ResponseEntity<List<Employee>> responseEntity = employeeController.searchEmployeeByName(txt,httpHeaders);
    assertNotNull(responseEntity.getBody());
    assertEquals(employeeList.size(), responseEntity.getBody().size());
  }
}