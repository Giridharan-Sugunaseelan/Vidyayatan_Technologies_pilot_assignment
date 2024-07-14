package com.vidyayatantechnologies.assignment.service;

import com.vidyayatantechnologies.assignment.dto.PermissionDto;
import com.vidyayatantechnologies.assignment.entities.Permission;
import com.vidyayatantechnologies.assignment.exception.PermissionNotFoundException;
import com.vidyayatantechnologies.assignment.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissionService {

    private PermissionRepository permissionRepository;

    private ModelMapper modelMapper;

    public PermissionDto addPermission(PermissionDto dto){
        Permission permission = this.modelMapper.map(dto, Permission.class);
        Permission saved = this.permissionRepository.save(permission);
        return this.modelMapper.map(saved, PermissionDto.class);
    }

    public PermissionDto getPermission(Integer permission_id){
        Permission permission = this.permissionRepository.findById(permission_id).orElseThrow(() -> new PermissionNotFoundException("Permission Not Found"));
        return this.modelMapper.map(permission, PermissionDto.class);
    }

    public List<PermissionDto> getAllPermissions(){
        List<Permission> permissions = this.permissionRepository.findAll();
        return permissions.stream().map((permission) -> this.modelMapper.map(permission, PermissionDto.class)).toList();
    }

    public PermissionDto updatePermission(PermissionDto dto, Integer permission_id){
        Permission newPermission = this.permissionRepository.findById(permission_id).orElseThrow(() -> new PermissionNotFoundException("Permission Not Found"));
        Permission permission = this.modelMapper.map(dto, Permission.class);
        newPermission.setName(permission.getName());
        Permission updatedPermission = this.permissionRepository.save(newPermission);
        return this.modelMapper.map(updatedPermission, PermissionDto.class);
    }

    public Boolean deletePermission(Integer permission_id){
        this.permissionRepository.deleteById(permission_id);
        return true;
    }
}
