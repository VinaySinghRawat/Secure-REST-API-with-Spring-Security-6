package com.project.SecurityApp.SecurityApplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.SecurityApp.SecurityApplication.entities.Session;
import com.project.SecurityApp.SecurityApplication.entities.User;


public interface SessionRepository extends JpaRepository<Session, Long> {
	  List<Session> findByUser(User user);

	    Optional<Session> findByRefreshToken(String refreshToken);
}
