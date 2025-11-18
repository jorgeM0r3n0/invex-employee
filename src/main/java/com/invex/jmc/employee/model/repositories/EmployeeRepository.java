package com.invex.jmc.employee.model.repositories;

import com.invex.jmc.employee.model.entities.EmployeeEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link EmployeeEntity} persistence operations.
 *
 * <p>This interface extends {@link JpaRepository} to provide CRUD operations,
 * pagination, and query support for employee records.</p>
 *
 * <p>Additional custom query methods are defined to support lookups
 * based on employee identifiers and full-name searches.</p>
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
  /**
   * Retrieves an employee by its unique identifier.
   *
   * @param idEmployee the employee ID to search for
   * @return an {@link Optional} containing the matching {@link EmployeeEntity},
   *         or an empty optional if no employee is found
   */
  Optional<EmployeeEntity> findEmployeeByIdEmployee(String idEmployee);

  /**
   * Searches for employees by matching the provided name against a full
   * concatenation of first name, middle name, paternal surname, and maternal surname.
   *
   * <p>The comparison is case-insensitive and supports partial matches.</p>
   *
   * @param name the name or partial name to search for
   * @return a list of {@link EmployeeEntity} objects whose combined name fields
   *         contain the specified search term
   */
  @Query("""
        SELECT e FROM EmployeeEntity e
        WHERE LOWER(CONCAT(
            COALESCE(e.firstName, ''), ' ',
            COALESCE(e.middleName, ''), ' ',
            COALESCE(e.paternalSurname, ''), ' ',
            COALESCE(e.maternalSurname, '')
        )) LIKE LOWER(CONCAT('%', :name, '%'))
        """)
    List<EmployeeEntity> searchByFullName(@Param("name") String name);
}
