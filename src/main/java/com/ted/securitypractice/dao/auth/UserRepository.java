package com.ted.securitypractice.dao.auth;

import com.ted.securitypractice.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findOneWithAuthoritiesByUserName(String userName);
}
