package com.vidyayatantechnologies.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Integer role_id;

    private String name;

    private List<PermissionDto> permissions;

    public RoleDto(String name, List<PermissionDto> permissions) {
        this.name = name;
        this.permissions = permissions;
    }
}
