package com.invex.jmc.employee.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Represents a job position within the system.
 *
 * <p>This entity maps to the {@code cat_job_position} table and stores basic
 * information related to an employee's job position, including its unique
 * identifier, code, and descriptive name.</p>
 *
 * <p>The class uses Lombok's {@link lombok.Data @Data} annotation to
 * automatically generate getters, setters, and other utility methods.</p>
 */
@Entity
@Table(name = "cat_job_position")
@Data
public class JobPositionEntity {
  @Id
  @Column(name = "id_job_position", length = 36, nullable = false)
  private String idJobPosition;

  @Column(name = "code", length = 3, nullable = false)
  private String code;

  @Column(name = "description", length = 100, nullable = false)
  private String description;
}
