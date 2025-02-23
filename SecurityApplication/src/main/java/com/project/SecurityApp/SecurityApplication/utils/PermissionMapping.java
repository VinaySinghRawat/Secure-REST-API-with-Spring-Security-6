package com.project.SecurityApp.SecurityApplication.utils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.project.SecurityApp.SecurityApplication.entities.enums.Permission;
import com.project.SecurityApp.SecurityApplication.entities.enums.Role;
import  static com.project.SecurityApp.SecurityApplication.entities.enums.Role.*;
import  static com.project.SecurityApp.SecurityApplication.entities.enums.Permission.*;


public class PermissionMapping {
	
	
	private static final Map<Role, Set<Permission>> map = Map.of(
            USER, Set.of(USER_VIEW,POST_VIEW),
            CREATOR, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE),
            ADMIN, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE, USER_DELETE, USER_CREATE, POST_DELETE)
    );


    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role) {
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }

}
