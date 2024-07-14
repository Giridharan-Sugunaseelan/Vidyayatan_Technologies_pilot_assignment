package com.vidyayatantechnologies.assignment.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Per_Role_Map")
public class PerRoleMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer per_role_id;

    @ManyToOne(targetEntity = Permission.class)
    @JoinColumn(name = "permissionId", referencedColumnName = "permission_id")
    private Permission permission;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "roleId", referencedColumnName = "role_id")
    private Role role;

    @JoinColumn(nullable = false)
    private Boolean status;

    public PerRoleMap(Permission permission, Role role, Boolean status) {
        this.permission = permission;
        this.role = role;
        this.status = status;
    }
}
