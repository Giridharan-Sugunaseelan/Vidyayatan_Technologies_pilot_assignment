package com.vidyayatantechnologies.assignment.controller;


import com.vidyayatantechnologies.assignment.annotations.Permissions;
import com.vidyayatantechnologies.assignment.dto.PermissionDto;
import com.vidyayatantechnologies.assignment.enums.LogicEnum;
import com.vidyayatantechnologies.assignment.enums.PermissionsEnum;
import com.vidyayatantechnologies.assignment.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@AllArgsConstructor
public class PermissionController {

    private PermissionService permissionService;

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @PostMapping
    public ResponseEntity<PermissionDto> addPermission(@RequestBody PermissionDto dto){
        PermissionDto permissionDto = this.permissionService.addPermission(dto);
        return new ResponseEntity<>(permissionDto, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @GetMapping("/{permission_id}")
    public ResponseEntity<PermissionDto> getPermission(@PathVariable Integer permission_id){
        PermissionDto permissionDto = this.permissionService.getPermission(permission_id);
        return new ResponseEntity<>(permissionDto, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAllPermissions(){
        List<PermissionDto> allPermissions = this.permissionService.getAllPermissions();
        return new ResponseEntity<>(allPermissions, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @PutMapping("/{permission_id}")
    public ResponseEntity<PermissionDto> updatePermission(@RequestBody PermissionDto dto, @PathVariable Integer permission_id){
        PermissionDto permissionDto = this.permissionService.updatePermission(dto, permission_id);
        return new ResponseEntity<>(permissionDto, HttpStatus.OK);
    }

    @Permissions(permissions = {PermissionsEnum.AllowWrite, PermissionsEnum.AllowUpdate}, type = LogicEnum.ALL)
    @DeleteMapping("/{permission_id}")
    public ResponseEntity<Boolean> deletePermission(@PathVariable Integer permission_id){
        Boolean status = this.permissionService.deletePermission(permission_id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
