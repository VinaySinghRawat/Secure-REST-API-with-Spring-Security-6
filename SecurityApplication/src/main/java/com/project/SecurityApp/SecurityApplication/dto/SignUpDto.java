package com.project.SecurityApp.SecurityApplication.dto;

import java.util.Set;

import com.project.SecurityApp.SecurityApplication.entities.enums.Permission;
import com.project.SecurityApp.SecurityApplication.entities.enums.Role;

import lombok.Data;

@Data
public class SignUpDto {
	 private String email;
	    private String password;
 	    private String name;
	   private Set<Role> roles;
	   private Set<Permission> permissions;
}
