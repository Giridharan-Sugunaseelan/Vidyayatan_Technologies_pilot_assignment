package com.vidyayatantechnologies.assignment.service;

import com.vidyayatantechnologies.assignment.dto.PermissionDto;
import com.vidyayatantechnologies.assignment.dto.RoleDto;
import com.vidyayatantechnologies.assignment.entities.PerRoleMap;
import com.vidyayatantechnologies.assignment.entities.Permission;
import com.vidyayatantechnologies.assignment.entities.Role;
import com.vidyayatantechnologies.assignment.exception.RoleNotFoundException;
import com.vidyayatantechnologies.assignment.repository.PerRoleRepository;
import com.vidyayatantechnologies.assignment.repository.PermissionRepository;
import com.vidyayatantechnologies.assignment.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    private PermissionRepository permissionRepository;

    private PerRoleRepository perRoleRepository;

    private PerRoleMapService perRoleMapService;

    private ModelMapper modelMapper;

    public RoleDto addRole(RoleDto dto){
        Role role = new Role();
        role.setName(dto.getName());
        Role saved = this.roleRepository.save(role);
        List<Permission> permissions = dto.getPermissions().stream().map((permissionDto) -> this.modelMapper.map(permissionDto, Permission.class)).toList();
        permissions.forEach((permission -> this.perRoleRepository.save(new PerRoleMap(permission, saved, true))));
        RoleDto roleDto = this.modelMapper.map(saved, RoleDto.class);
        List<PermissionDto> permissionsDto = this.perRoleMapService.getPermissionDtoByRole(saved);
        roleDto.setPermissions(permissionsDto);
        return roleDto;
    }

    public RoleDto getRole(Integer role_id){
        Role role = this.roleRepository.findById(role_id).orElseThrow(() -> new RoleNotFoundException("Role Not Found!!!"));
        RoleDto roleDto = this.modelMapper.map(role, RoleDto.class);
        List<PermissionDto> permissionsDtos = this.perRoleMapService.getPermissionDtoByRole(role);
        roleDto.setPermissions(permissionsDtos);
        return roleDto;
    }

    public List<RoleDto> getAllRoles(){
        List<Role> roles = this.roleRepository.findAll();
        return roles.stream().map((role) -> {
            RoleDto roleDto = this.modelMapper.map(role, RoleDto.class);
            List<PermissionDto> permissionDtos = this.perRoleMapService.getPermissionDtoByRole(role);
            roleDto.setPermissions(permissionDtos);
            return roleDto;
        }).toList();
    }

    public RoleDto updateRole(RoleDto dto, Integer role_id){
        Role oldRole = this.roleRepository.findById(role_id).orElseThrow(() -> new RoleNotFoundException("Role Not Found!!!"));
        this.perRoleRepository.deleteByRole(oldRole);
        oldRole.setName(dto.getName());
        this.roleRepository.save(oldRole);
        List<Permission> permissions = dto.getPermissions().stream().map((permissionDto -> this.modelMapper.map(permissionDto, Permission.class))).toList();
        permissions.forEach((permission -> this.perRoleRepository.save(new PerRoleMap(permission, oldRole, true))));
        List<PermissionDto> permissionsDto = this.perRoleMapService.getPermissionDtoByRole(oldRole);
        RoleDto roleDto = this.modelMapper.map(oldRole, RoleDto.class);
        roleDto.setPermissions(permissionsDto);
        return roleDto;
    }

    public Boolean deleteRole(Integer role_id){
        this.roleRepository.deleteById(role_id);
        return true;
    }
}
