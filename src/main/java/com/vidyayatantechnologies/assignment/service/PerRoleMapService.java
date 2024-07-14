package com.vidyayatantechnologies.assignment.service;

import com.vidyayatantechnologies.assignment.dto.PermissionDto;
import com.vidyayatantechnologies.assignment.entities.PerRoleMap;
import com.vidyayatantechnologies.assignment.entities.Permission;
import com.vidyayatantechnologies.assignment.entities.Role;
import com.vidyayatantechnologies.assignment.repository.PerRoleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerRoleMapService {

    private PerRoleRepository perRoleRepository;

    private ModelMapper modelMapper;

    public List<PermissionDto> getPermissionDtoByRole(Role role) {
        List<PerRoleMap> permissionsByRole = this.perRoleRepository.findPermissionsByRole(role);
        List<Permission> permissions = permissionsByRole.stream().map(PerRoleMap::getPermission).toList();
        return permissions.stream().map((permission -> this.modelMapper.map(permission, PermissionDto.class))).toList();
    }

    public List<Permission> getPermissionsByRole(Role role){
        List<PerRoleMap> permissionsByRole = this.perRoleRepository.findPermissionsByRole(role);
        return permissionsByRole.stream().map(PerRoleMap::getPermission).toList();
    }

}
