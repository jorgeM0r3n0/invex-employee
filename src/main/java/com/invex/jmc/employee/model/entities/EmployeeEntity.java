package com.invex.jmc.employee.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an employee entity stored in the system.
 *
 * <p>This class maps to the {@code employee} table and contains personal,
 * demographic, and job-related information. It is used for persistence
 * operations through JPA/Hibernate.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity {

  /**
   * Unique identifier of the employee.
   * Stored as a 36-character string (UUID format).
   */
  @Id
  @Column(name = "id_employee", length = 36)
  private String idEmployee;

  /**
   * EmployeeRequest's first name.
   * Maximum length: 100 characters.
   */
  @Column(name = "first_name", length = 100)
  private String firstName;

  /**
   * EmployeeRequest's middle name.
   * Optional field.
   * Maximum length: 100 characters.
   */
  @Column(name = "middle_name", length = 100)
  private String middleName;

  /**
   * EmployeeRequest's paternal surname.
   * Maximum length: 100 characters.
   */
  @Column(name = "paternal_surname", length = 100)
  private String paternalSurname;

  /**
   * EmployeeRequest's maternal surname.
   * Maximum length: 100 characters.
   */
  @Column(name = "maternal_surname", length = 100)
  private String maternalSurname;

  /**
   * EmployeeRequest's sex.
   * Many-to-one relationship with {@link SexEntity}.
   */
  @ManyToOne
  @JoinColumn(name = "id_sex")
  private SexEntity sex;

  /**
   * EmployeeRequest's job position.
   * Many-to-one relationship with {@link JobPositionEntity}.
   */
  @ManyToOne
  @JoinColumn(name = "id_job_position")
  private JobPositionEntity jobPosition;

  /**
   * EmployeeRequest's date of birth.
   */
  @Column(name = "birth_day")
  private LocalDate birthDay;

  /**
   * EmployeeRequest's status.
   */
  @Column(name = "status")
  private int status;

  /**
   * EmployeeRequest's ts.
   */
  @Column(name = "ts")
  private LocalDateTime ts;
}