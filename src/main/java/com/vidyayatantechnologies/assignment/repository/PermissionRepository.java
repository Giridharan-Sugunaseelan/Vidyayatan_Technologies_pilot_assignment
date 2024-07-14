package com.vidyayatantechnologies.assignment.repository;

import com.vidyayatantechnologies.assignment.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    boolean existsByName(String name);
}
