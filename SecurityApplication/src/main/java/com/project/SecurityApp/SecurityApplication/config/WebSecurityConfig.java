package com.project.SecurityApp.SecurityApplication.config;

import org.apache.catalina.startup.HomesUserDatabase;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.SecurityApp.SecurityApplication.filters.JwtAuthFilter;
import com.project.SecurityApp.SecurityApplication.handler.OAuth2SuccessHandler;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import static com.project.SecurityApp.SecurityApplication.entities.enums.Role.ADMIN;
import static com.project.SecurityApp.SecurityApplication.entities.enums.Role.CREATOR;;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	private final JwtAuthFilter jwtAuthFilter;

    private final OAuth2SuccessHandler oAuth2SuccessHandler;
	private static final String[] publicRoutes = {
            "/error", "/auth/**", "/home.html"
    };
	@Bean
   SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
	   httpSecurity
       .authorizeHttpRequests(auth -> auth
    		   .requestMatchers(publicRoutes).permitAll()
      		   .requestMatchers("/posts/**").authenticated()
      		  .anyRequest().authenticated())
       .csrf(csrfConfig -> csrfConfig.disable())
       .sessionManagement(sessionConfig -> sessionConfig
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	   .oauth2Login(oauth2Config -> oauth2Config
               .failureUrl("/login?error=true")
            .successHandler(oAuth2SuccessHandler)
       );
	   
return httpSecurity.build();
   }
	
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
