package com.invex.jmc.employee.model.repositories;

import com.invex.jmc.employee.model.entities.SexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing {@link SexEntity} data.
 *
 * <p>Extends {@link org.springframework.data.jpa.repository.JpaRepository} to provide
 * common CRUD operations without the need for boilerplate code.</p>
 *
 * <p>This repository handles persistence operations for the {@code cat_sex} table.</p>
 */
@Repository
public interface SexRepository extends JpaRepository<SexEntity, String> {
}
