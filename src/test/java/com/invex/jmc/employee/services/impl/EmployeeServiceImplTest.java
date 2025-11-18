package com.invex.jmc.employee.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("/invexPropertiesTest.properties")
@ActiveProfiles("test")
class EmployeeServiceImplTest {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  @Value("${idEmployee}")
  String idEmployee;
  @Value("${employees.list}")
  String employeesJson;
  @Value("${employeesRequest}")
  String employeesRequestJson;
  @MockBean
  private EmployeeRepository employeeRepository;
  @MockBean
  private JobPositionRepository jobPositionRepository;
  @MockBean
  private SexRepository sexRepository;
  private EmployeeMapper employeeMapper;
  private MapperUtil mapperUtil;
  @Autowired
  private EmployeeService employeeService;
  private List<Employee> employeeList;
  private List<EmployeeEntity> employeeEntityList;
  private EmployeeEntity employeeEntity;
  private EmployeesRequest employeesRequest;

  @BeforeEach
  void setUp() {
    mapperUtil = new MapperUtil(new ModelMapper());
    employeeMapper = new EmployeeMapper();
    /*
    employeeService = new EmployeeServiceImpl(employeeRepository,
      jobPositionRepository,
      sexRepository, mapperUtil,
      employeeMapper);
     */
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    try {
      employeeList = mapper.readValue(employeesJson, new TypeReference<List<Employee>>() {
      });
      employeesRequest = mapper.readValue(employeesRequestJson,
        new TypeReference<EmployeesRequest>() {
        });
      employeeEntityList = mapperUtil.mapList(employeeList, EmployeeEntity.class);
      employeeEntity = employeeEntityList.get(0);
    }
    catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getAllEmployee() {
    when(employeeRepository.findAll()).thenReturn(employeeEntityList);
    List<Employee> employeeList = employeeService.getAllEmployee();
    assertNotNull(employeeList);
    assertEquals(2, employeeList.size());
  }

  @Test
  void getEmployeeById() {
    when(employeeRepository.findEmployeeByIdEmployee(idEmployee)).thenReturn(
      Optional.of(employeeEntity));
    Employee employee = employeeService.getEmployeeById(idEmployee);
    assertNotNull(employee);
  }

  @Test
  void getEmployeeById_NotFound() {
    when(employeeRepository.findEmployeeByIdEmployee(idEmployee)).thenReturn(
      Optional.empty());
    assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(idEmployee));
    verify(employeeRepository, times(1)).findEmployeeByIdEmployee(idEmployee);
  }
  @Test
  void addEmployees_SexNotFound() {
    EmployeeRequest employeeRequest = employeesRequest.getEmployees().get(0);
    when(sexRepository.findById(employeeRequest.getIdSex())).thenReturn(Optional.empty());
    assertThrows(SexNotFoundException.class, () -> employeeService.addEmployees(employeesRequest));
    verify(sexRepository, times(1)).findById(employeeRequest.getIdSex());
  }

  @Test
  void addEmployees_JobPositionNotFound() {
    EmployeeRequest employeeRequest = employeesRequest.getEmployees().get(0);
    String idSex = employeeRequest.getIdSex();
    SexEntity sexEntity = new SexEntity();
    sexEntity.setIdSex(idSex);
    sexEntity.setCode("M");
    sexEntity.setDescription("Male");
    when(sexRepository.findById(employeeRequest.getIdSex())).thenReturn(Optional.of(sexEntity));

    when(jobPositionRepository.findById(employeeRequest.getIdJobPosition())).thenReturn(Optional.empty());
    assertThrows(JobPositionNotFoundException.class, () -> employeeService.addEmployees(employeesRequest));
    verify(jobPositionRepository, times(1)).findById(employeeRequest.getIdJobPosition());
  }

  @Test
  void addEmployees() {
    EmployeeRequest employeeRequest = employeesRequest.getEmployees().get(0);
    SexEntity sexEntity = new SexEntity();
    sexEntity.setIdSex("50284c0c-c0fd-11f0-9884-ae32cdf1c2ae");
    JobPositionEntity jobEntity = new JobPositionEntity();
    jobEntity.setIdJobPosition("84d89a55-c0fd-11f0-9884-ae32cdf1c2ae");
    EmployeeEntity mappedEntity = new EmployeeEntity();
    mappedEntity.setFirstName("Jorge");
    EmployeeEntity savedEntity = new EmployeeEntity();
    savedEntity.setIdEmployee("UUID-GENERATED");
    savedEntity.setFirstName("Jorge");
    savedEntity.setSex(sexEntity);
    savedEntity.setJobPosition(jobEntity);
    Employee dto = new Employee();
    dto.setIdEmployee("UUID-GENERATED");
    dto.setFirstName("Jorge");

    when(employeeRepository.saveAll(anyList())).thenReturn(Collections.singletonList(savedEntity));

    when(sexRepository.findById(employeeRequest.getIdSex())).thenReturn(Optional.of(sexEntity));
    when(jobPositionRepository.findById(employeeRequest.getIdJobPosition())).thenReturn(
      Optional.of(jobEntity));

    List<Employee> employeeList1 = employeeService.addEmployees(employeesRequest);

    assertNotNull(employeeList1);
    assertEquals(1, employeeList1.size());
    assertEquals("UUID-GENERATED", employeeList1.get(0).getIdEmployee());

    // Verificaciones
    verify(sexRepository).findById("50284c0c-c0fd-11f0-9884-ae32cdf1c2ae");
    verify(jobPositionRepository).findById("84d89a55-c0fd-11f0-9884-ae32cdf1c2ae");
    verify(employeeRepository).saveAll(anyList());
  }

  @Test
  void updateEmployee_SexNotFound() {
    String idEmployee = "UUID-GENERATED";
    EmployeeRequest employeeRequest = employeesRequest.getEmployees().get(0);
    employeeRequest.setIdEmployee(idEmployee);

    String idSex = employeeRequest.getIdSex();
    String idJobPosition = employeeRequest.getIdJobPosition();

    SexEntity sexEntity = new SexEntity();
    sexEntity.setIdSex(idSex);
    sexEntity.setCode("M");
    sexEntity.setDescription("Male");

    JobPositionEntity jobEntity = new JobPositionEntity();
    jobEntity.setIdJobPosition(idJobPosition);
    jobEntity.setCode("CEO");
    jobEntity.setDescription("Chief Executive Officer");

    EmployeeEntity employeeEntity;
    employeeEntity = new EmployeeEntity();
    employeeEntity.setIdEmployee(idEmployee);
    employeeEntity.setFirstName(employeeRequest.getFirstName());
    employeeEntity.setMaternalSurname(employeeRequest.getMaternalSurname());
    employeeEntity.setPaternalSurname(employeeRequest.getPaternalSurname());
    employeeEntity.setBirthDay(LocalDate.parse(employeeRequest.getBirthDay(), formatter));
    employeeEntity.setStatus(1);
    employeeEntity.setTs(LocalDateTime.now());
    employeeEntity.setSex(sexEntity);
    employeeEntity.setJobPosition(jobEntity);


    when(employeeRepository.findById(idEmployee)).thenReturn(
      Optional.of(employeeEntity));

    when(jobPositionRepository.findById(anyString())).thenReturn(Optional.of(jobEntity));

    when(sexRepository.findById(anyString())).thenReturn(Optional.empty());

    assertThrows(SexNotFoundException.class,
      () -> employeeService.updateEmployee(idEmployee, employeeRequest));
    verify(sexRepository, times(1)).findById(idSex);
  }

  @Test
  void updateEmployee_JobPositionNotFound() {
    String idEmployee = "UUID-GENERATED";
    EmployeeRequest employeeRequest = employeesRequest.getEmployees().get(0);
    employeeRequest.setIdEmployee(idEmployee);

    String idSex = employeeRequest.getIdSex();
    String idJobPosition = employeeRequest.getIdJobPosition();

    SexEntity sexEntity = new SexEntity();
    sexEntity.setIdSex(idSex);
    sexEntity.setCode("M");
    sexEntity.setDescription("Male");

    JobPositionEntity jobEntity = new JobPositionEntity();
    jobEntity.setIdJobPosition(idJobPosition);
    jobEntity.setCode("CEO");
    jobEntity.setDescription("Chief Executive Officer");

    EmployeeEntity employeeEntity;
    employeeEntity = new EmployeeEntity();
    employeeEntity.setIdEmployee(idEmployee);
    employeeEntity.setFirstName(employeeRequest.getFirstName());
    employeeEntity.setMaternalSurname(employeeRequest.getMaternalSurname());
    employeeEntity.setPaternalSurname(employeeRequest.getPaternalSurname());
    employeeEntity.setBirthDay(LocalDate.parse(employeeRequest.getBirthDay(), formatter));
    employeeEntity.setStatus(1);
    employeeEntity.setTs(LocalDateTime.now());
    employeeEntity.setSex(sexEntity);
    employeeEntity.setJobPosition(jobEntity);


    when(employeeRepository.findById(idEmployee)).thenReturn(
      Optional.of(employeeEntity));

    when(jobPositionRepository.findById(anyString())).thenReturn(Optional.empty());

    assertThrows(JobPositionNotFoundException.class,
      () -> employeeService.updateEmployee(idEmployee, employeeRequest));
    verify(jobPositionRepository, times(1)).findById(idJobPosition);
  }

  @Test
  void updateEmployee_EmployeeNotFound() {
    String idEmployee = "UUID-GENERATED";
    EmployeeRequest employeeRequest = employeesRequest.getEmployees().get(0);
    when(employeeRepository.findEmployeeByIdEmployee(idEmployee)).thenReturn(
      Optional.empty());
    assertThrows(EmployeeNotFoundException.class,
      () -> employeeService.updateEmployee(idEmployee, employeeRequest));
    verify(employeeRepository, times(1)).findById(idEmployee);
  }

  @Test
  void updateEmployee() {
    String idEmployee = "UUID-GENERATED";
    EmployeeRequest employeeRequest = employeesRequest.getEmployees().get(0);
    employeeRequest.setIdEmployee(idEmployee);

    String idSex = employeeRequest.getIdSex();
    String idJobPosition = employeeRequest.getIdJobPosition();

    SexEntity sexEntity = new SexEntity();
    sexEntity.setIdSex(idSex);
    sexEntity.setCode("M");
    sexEntity.setDescription("Male");

    JobPositionEntity jobEntity = new JobPositionEntity();
    jobEntity.setIdJobPosition(idJobPosition);
    jobEntity.setCode("CEO");
    jobEntity.setDescription("Chief Executive Officer");

    EmployeeEntity employeeEntity;
    employeeEntity = new EmployeeEntity();
    employeeEntity.setIdEmployee(idEmployee);
    employeeEntity.setFirstName(employeeRequest.getFirstName());
    employeeEntity.setMaternalSurname(employeeRequest.getMaternalSurname());
    employeeEntity.setPaternalSurname(employeeRequest.getPaternalSurname());
    employeeEntity.setBirthDay(LocalDate.parse(employeeRequest.getBirthDay(), formatter));
    employeeEntity.setStatus(1);
    employeeEntity.setTs(LocalDateTime.now());
    employeeEntity.setSex(sexEntity);
    employeeEntity.setJobPosition(jobEntity);

    Employee dto = new Employee();
    dto.setIdEmployee(idEmployee);
    dto.setFirstName("Jorge");

    when(employeeRepository.findById(idEmployee)).thenReturn(Optional.of(employeeEntity));

    when(jobPositionRepository.findById(idJobPosition)).thenReturn(Optional.of(jobEntity));

    when(sexRepository.findById(idSex)).thenReturn(Optional.of(sexEntity));

    when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);

    Employee result = employeeService.updateEmployee(idEmployee, employeeRequest);

    assertNotNull(result);
    assertEquals(idEmployee, result.getIdEmployee());
  }

  @Test
  void deleteEmployee() {
    String idEmployee = "UUID-GENERATED";
    EmployeeEntity entity = new EmployeeEntity();
    entity.setIdEmployee(idEmployee);
    when(employeeRepository.findById(idEmployee)).thenReturn(Optional.of(entity));
    employeeService.deleteEmployee(idEmployee);
    verify(employeeRepository).delete(entity);
  }

  @Test
  void deleteEmployee_not_found() {
    String idEmployee = "UUID-GENERATED";
    when(employeeRepository.findById(idEmployee)).thenReturn(Optional.empty());
    assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(idEmployee));
    verify(employeeRepository, never()).delete(any());
  }

  @Test
  void searchEmployeeByName() {
    String name = "Juan";

    // Datos simulados del repositorio
    EmployeeEntity entity = new EmployeeEntity();
    entity.setIdEmployee("123");
    entity.setFirstName("Juan");

    List<EmployeeEntity> entityList = List.of(entity);
    Employee dto = new Employee();
    dto.setIdEmployee("123");
    dto.setFirstName("Juan");

    List<Employee> dtoList = List.of(dto);
    when(employeeRepository.searchByFullName(name))
      .thenReturn(entityList);
    List<Employee> result = employeeService.searchEmployeeByName(name);

    assertEquals(1, result.size());
    assertEquals("Juan", result.get(0).getFirstName());
    verify(employeeRepository, times(1)).searchByFullName(name);
  }
}