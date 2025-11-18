package com.invex.jmc.employee.model.repositories;

import com.invex.jmc.employee.model.entities.EmployeeEntity;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Size;
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

  /**
   * Checks whether an employee already exists in the system with the specified full name.
   *
   * <p>This method performs a case-insensitive search comparing the first name,
   * middle name, paternal surname, and maternal surname of an employee. If any
   * existing record matches all four values, the method returns {@code true};
   * otherwise, it returns {@code false}.</p>
   *
   * <p>The comparison is performed using a custom JPQL query with normalized
   * (lowercased) values, ensuring that differences in letter case do not affect
   * the result.</p>
   *
   * @param firstName the employee's first name; must not exceed 100 characters.
   * @param middleName the employee's middle name; must not exceed 100 characters.
   * @param paternalSurname the employee's paternal surname; must not exceed 100 characters.
   * @param maternalSurname the employee's maternal surname; must not exceed 100 characters.
   * @return {@code true} if an employee with the specified full name already exists;
   *         {@code false} otherwise.
   */
  @Query("""
      SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END
      FROM EmployeeEntity e
      WHERE LOWER(e.firstName) = LOWER(:firstName)
        AND LOWER(e.middleName) = LOWER(:middleName)
        AND LOWER(e.paternalSurname) = LOWER(:paternalSurname)
        AND LOWER(e.maternalSurname) = LOWER(:maternalSurname)
      """)
  boolean existsByFullName(
      @Size(max = 100) String firstName, @Size(max = 100) String middleName,
      @Size(max = 100) String paternalSurname, @Size(max = 100) String maternalSurname);
}
