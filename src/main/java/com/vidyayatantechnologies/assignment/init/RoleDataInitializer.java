package com.vidyayatantechnologies.assignment.init;

import com.vidyayatantechnologies.assignment.dto.PermissionDto;
import com.vidyayatantechnologies.assignment.dto.RoleDto;
import com.vidyayatantechnologies.assignment.entities.PerRoleMap;
import com.vidyayatantechnologies.assignment.entities.Permission;
import com.vidyayatantechnologies.assignment.entities.Role;
import com.vidyayatantechnologies.assignment.repository.PerRoleRepository;
import com.vidyayatantechnologies.assignment.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(3)
@AllArgsConstructor
public class RoleDataInitializer implements CommandLineRunner {

    private RoleRepository roleRepository;

    private PerRoleRepository perRoleRepository;

    private ModelMapper modelMapper;

    @Override
    public void run(String... args) throws Exception {
        initialiseRoles();
    }

    public void initialiseRoles(){
        Permission allowRead = new Permission(1, "AllowRead");
        Permission allowWrite = new Permission(2, "AllowWrite");
        Permission allowUpdate = new Permission(3, "AllowUpdate");
        Permission allowDelete = new Permission(4, "AllowDelete");
        Permission allowBorrow = new Permission(5, "AllowBorrow");
        Permission allowReturn = new Permission(6, "AllowReturn");

        List<Permission> adminPermissions = Arrays.asList(allowRead, allowWrite, allowUpdate, allowDelete, allowBorrow, allowReturn);
        List<Permission> librarianPermissions = Arrays.asList(allowRead, allowUpdate, allowBorrow, allowReturn);
        List<Permission> memberPermissions = Arrays.asList(allowRead, allowBorrow, allowReturn);

        Role admin = new Role("Admin");
        Role librarian = new Role("Librarian");
        Role member = new Role("Member");

        createRoleIfNotExists(admin, adminPermissions);
        createRoleIfNotExists(librarian, librarianPermissions);
        createRoleIfNotExists(member, memberPermissions);
    }

    public void createRoleIfNotExists(Role role, List<Permission> permissions){
        if(!this.roleRepository.existsByName(role.getName())){
            this.roleRepository.save(role);
            permissions.forEach((permission -> this.perRoleRepository.save(new PerRoleMap(permission, role, true))));
        }
    }
}
