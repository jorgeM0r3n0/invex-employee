package com.invex.jmc.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.invex.jmc.employee.constants.ApiDescriptionsConstant;
import com.invex.jmc.employee.constants.ValidationMessagesConstant;
import com.invex.jmc.employee.util.CalculatesUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * Represents an employee within the system.
 *
 * <p>This class contains the basic information of an employee, including
 * their unique identifier, names, surnames, sex, job position,
 * date of birth, active status, and timestamp.
 * </p>
 *
 * <p>Bean Validation annotations are used to enforce data integrity,
 * and Jackson annotations are used to format dates when serializing to JSON.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents an employee with personal and job information")
public class Employee {
  /**
   * Unique identifier of the employee.
   *
   * <p>Optional during creation but required for updates. Must contain a UUID string
   * of up to 36 characters.</p>
   */
  @Size(min = ValidationMessagesConstant.MIN_SIZE_36, max =
      ValidationMessagesConstant.MAX_SIZE_36, message =
      ValidationMessagesConstant.ID_EMPLOYEE_SIZE_36)
  @NotNull
  @Schema(
      description = ApiDescriptionsConstant.EMPLOYEE_ID_DESC,
      example = ApiDescriptionsConstant.EMPLOYEE_ID_EXAMPLE,
      maxLength = ValidationMessagesConstant.MIN_SIZE_36,
      minLength = ValidationMessagesConstant.MAX_SIZE_36,
      required = true
  )
  private String idEmployee;

  @Size(max = 100)
  @Schema(description = "First name of the employee", example = "John")
  private String firstName;

  @Size(max = 100)
  @Schema(description = "Middle name of the employee", example = "William")
  private String middleName;

  @Size(max = 100)
  @Schema(description = "Paternal surname of the employee", example = "Smith")
  private String paternalSurname;

  @Size(max = 100)
  @Schema(description = "Maternal surname of the employee", example = "Johnson")
  private String maternalSurname;

  @NotNull
  @Schema(description = "Sex of the employee", required = true)
  private Sex sex;

  @NotNull
  @Schema(description = "Job position of the employee", required = true)
  private JobPosition jobPosition;

  @Column(name = "birth_day")
  @JsonFormat(pattern = "dd/MM/yyyy")
  @Schema(description = "Birth date of the employee", example = "15/05/1990")
  private LocalDate birthDay;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @Schema(description = "Age of the employee, calculated from birthDay", example = "33",
      accessMode = Schema.AccessMode.READ_ONLY)
     private int age;

  @Schema(description = "Active status of the employee", example = "true")
  private boolean status;

  @Column(name = "ts")
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Schema(description = "Timestamp of the employee's creation or last update", example = "14/11"
      + "/2025 09:30:00")
  private LocalDateTime ts;

  /**
   * Calculates and returns the age of the employee.
   *
   * @return the age of the employee in years
   * @throws IllegalArgumentException if {@link #birthDay} is null
   */
  public int getAge() {
    return CalculatesUtil.calculateAge(birthDay);
  }
}
