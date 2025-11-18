package com.invex.jmc.employee.model.repositories;

import com.invex.jmc.employee.model.entities.JobPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link JobPositionEntity} persistence operations.
 *
 * <p>This interface extends {@link JpaRepository}, providing CRUD operations,
 * pagination, and query generation capabilities for job position records.</p>
 *
 * <p>Since no custom queries are defined, all data access operations rely on the
 * default methods inherited from {@code JpaRepository}.</p>
 */
@Repository
public interface JobPositionRepository extends JpaRepository<JobPositionEntity, String> {
}
