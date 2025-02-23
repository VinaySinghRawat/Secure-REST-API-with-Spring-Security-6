package com.project.SecurityApp.SecurityApplication.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.SecurityApp.SecurityApplication.entities.enums.Permission;
import com.project.SecurityApp.SecurityApplication.entities.enums.Role;
import com.project.SecurityApp.SecurityApplication.utils.PermissionMapping;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
public class User implements UserDetails{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
//    
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    private Set<Permission> permissions;
   
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		  Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        roles.forEach(
	                role -> {
	                    Set<SimpleGrantedAuthority> permissions = PermissionMapping.getAuthoritiesForRole(role);
	                    authorities.addAll(permissions);
	                     authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
	                }
	        );
	        return authorities;
//		Set<SimpleGrantedAuthority> authorities=roles.stream()
//				  .map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
//				  .collect(Collectors.toSet());
//	
//	permissions.forEach(
//			Permission->authorities.add(new SimpleGrantedAuthority(Permission.name()))
//			);
//	return authorities;
	}
	
	
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

}
