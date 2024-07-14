package com.vidyayatantechnologies.assignment.controller;

import com.vidyayatantechnologies.assignment.annotations.Permissions;
import com.vidyayatantechnologies.assignment.dto.RoleDto;
import com.vidyayatantechnologies.assignment.enums.LogicEnum;
import com.vidyayatantechnologies.assignment.enums.PermissionsEnum;
import com.vidyayatantechnologies.assignment.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @PostMapping
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto dto){
        RoleDto roleDto = this.roleService.addRole(dto);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @GetMapping("/{role_id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable Integer role_id){
        RoleDto roleDto = this.roleService.getRole(role_id);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> allRoles = this.roleService.getAllRoles();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @PutMapping("/{role_id}")
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto dto, @PathVariable Integer role_id){
        RoleDto roleDto = this.roleService.updateRole(dto, role_id);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @DeleteMapping("/{role_id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable Integer role_id){
        Boolean status = this.roleService.deleteRole(role_id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
