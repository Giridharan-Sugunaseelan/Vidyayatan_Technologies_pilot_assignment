package com.vidyayatantechnologies.assignment.init;

import com.vidyayatantechnologies.assignment.entities.Permission;
import com.vidyayatantechnologies.assignment.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@AllArgsConstructor
public class PermissionDataInitializer implements CommandLineRunner {

    private PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
        initiatePermissions();
    }

    public void initiatePermissions(){
        createPermissionIfNotExists("AllowRead");
        createPermissionIfNotExists("AllowWrite");
        createPermissionIfNotExists("AllowUpdate");
        createPermissionIfNotExists("AllowDelete");
        createPermissionIfNotExists("AllowBorrow");
        createPermissionIfNotExists("AllowReturn");
    }

    public void createPermissionIfNotExists(String roleName){
        if(!this.permissionRepository.existsByName(roleName)){
            Permission permission = new Permission();
            permission.setName(roleName);
            this.permissionRepository.save(permission);
        }
    }

}
