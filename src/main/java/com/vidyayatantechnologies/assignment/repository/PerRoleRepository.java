package com.vidyayatantechnologies.assignment.repository;

import com.vidyayatantechnologies.assignment.entities.PerRoleMap;
import com.vidyayatantechnologies.assignment.entities.Permission;
import com.vidyayatantechnologies.assignment.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerRoleRepository extends JpaRepository<PerRoleMap, Integer> {

    List<PerRoleMap> findPermissionsByRole(Role role);

    void deleteByRole(Role role);

    void deleteByRoleAndPermission(Role role, Permission permission);
}
