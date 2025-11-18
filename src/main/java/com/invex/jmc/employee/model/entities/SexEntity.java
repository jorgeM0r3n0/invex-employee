package com.invex.jmc.employee.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity that represents the Sex catalog.
 *
 * <p>This table defines the available sex options that can be associated
 * with an employee. Each entry includes an identifier, a short code, and
 * a descriptive label.</p>
 *
 * <p>Mapped to the {@code cat_sex} database table.</p>
 */
@Entity
@Table(name = "cat_sex")
@Data
public class SexEntity {
  /**
   * Unique identifier for the sex record.
   *
   * <p>Mapped to the {@code id_sex} column (UUID, 36 characters).</p>
   */
  @Id
  @Column(name = "id_sex", length = 36)
  private String idSex;

  /**
   * Short code representing the sex.
   *
   * <p>Typically one character, e.g., {@code 'M'}, {@code 'F'}, etc.</p>
   *
   * <p>Cannot be {@code null}.</p>
   */
  @Column(length = 1, nullable = false)
  private String code;

  /**
   * Human-readable description of the sex.
   *
   * <p>Examples: {@code "Male"}, {@code "Female"}, {@code "Other"}.</p>
   *
   * <p>Cannot be {@code null}.</p>
   */
  @Column(length = 50, nullable = false)
  private String description;

}
