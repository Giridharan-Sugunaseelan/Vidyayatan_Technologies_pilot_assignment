package com.vidyayatantechnologies.assignment.interceptors;

import com.vidyayatantechnologies.assignment.annotations.Permissions;
import com.vidyayatantechnologies.assignment.entities.PerRoleMap;
import com.vidyayatantechnologies.assignment.entities.Permission;
import com.vidyayatantechnologies.assignment.entities.Role;
import com.vidyayatantechnologies.assignment.entities.User;
import com.vidyayatantechnologies.assignment.enums.LogicEnum;
import com.vidyayatantechnologies.assignment.enums.PermissionsEnum;
import com.vidyayatantechnologies.assignment.exception.PermissionDeniedException;
import com.vidyayatantechnologies.assignment.repository.PerRoleRepository;
import com.vidyayatantechnologies.assignment.repository.UserRepository;
import com.vidyayatantechnologies.assignment.util.Principal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PermissionsInterceptor implements HandlerInterceptor {

    private PerRoleRepository perRoleRepository;
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod handlerMethod){
            Method method = handlerMethod.getMethod();

            Permissions permissions = method.getAnnotation(Permissions.class);
            if(permissions != null){
                List<PermissionsEnum> requiredPermissions = Arrays.asList(permissions.permissions());
                LogicEnum type = permissions.type();
                String email = Principal.getAuthentication().getName();
                User user = this.userRepository.findByEmail(email);
                Set<Role> roles = user.getRoles();
                Set<Permission> userPermissions = new HashSet<>();
                for(Role role : roles){
                    List<PerRoleMap> permissionsByRole = this.perRoleRepository.findPermissionsByRole(role);
                    permissionsByRole.forEach(perRoleMap -> userPermissions.add(perRoleMap.getPermission()));
                }

                Set<String> userPermissionNames = userPermissions.stream()
                        .map(Permission::getName)
                        .collect(Collectors.toSet());

                boolean hasPermission;
                if (type == LogicEnum.ALL) {
                    hasPermission = requiredPermissions.stream()
                            .allMatch(permission -> userPermissionNames.contains(permission.name()));
                } else {
                    hasPermission = requiredPermissions.stream()
                            .anyMatch(permission -> userPermissionNames.contains(permission.name()));
                }

                if(!hasPermission){
                    throw new PermissionDeniedException("Permission has been denied for this request");
                }
            }
        }
        return true;
    }
}
