package com.invex.jmc.employee.model.mapper;

import com.invex.jmc.employee.model.dto.request.EmployeeRequest;
import com.invex.jmc.employee.model.entities.EmployeeEntity;
import com.invex.jmc.employee.model.entities.JobPositionEntity;
import com.invex.jmc.employee.model.entities.SexEntity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.stereotype.Component;


/**
 * Mapper component responsible for updating an {@link EmployeeEntity}
 * instance using values provided in an {@link EmployeeRequest}.
 *
 * <p>This class centralizes the mapping logic between DTOs and entities,
 * ensuring consistent data transformation across the application.</p>
 */
@Component
public class EmployeeMapper {

  /**
   * Updates an existing {@link EmployeeEntity} using the fields provided
   * in an {@link EmployeeRequest}. Only non-null fields in the request
   * are applied to the entity.
   *
   * <p>The method also sets the related {@link SexEntity} and
   * {@link JobPositionEntity} references. The birth date is parsed from
   * a string using the format <strong>dd/MM/yyyy</strong>.</p>
   *
   * @param e    the existing employee entity to update (must not be null)
   * @param r    the request object containing the new values (must not be null)
   * @param sex  the resolved sex entity associated with the employee
   * @param job  the resolved job position entity associated with the employee
   *
   * @throws java.time.format.DateTimeParseException if the birth date format is invalid
   */
  public void updateEntityFromRequest(EmployeeEntity e,
                                      EmployeeRequest r,
                                      SexEntity sex,
                                      JobPositionEntity job) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Optional.ofNullable(r.getFirstName()).ifPresent(e::setFirstName);
    Optional.ofNullable(r.getMiddleName()).ifPresent(e::setMiddleName);
    Optional.ofNullable(r.getPaternalSurname()).ifPresent(e::setPaternalSurname);
    Optional.ofNullable(r.getMaternalSurname()).ifPresent(e::setMaternalSurname);
    Optional.of(LocalDate.parse(r.getBirthDay(), formatter)).ifPresent(e::setBirthDay);
    Optional.of(r.getStatus()).ifPresent(e::setStatus);
    e.setSex(sex);
    e.setJobPosition(job);
  }
}
