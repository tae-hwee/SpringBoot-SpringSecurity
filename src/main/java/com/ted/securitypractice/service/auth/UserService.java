package com.ted.securitypractice.service.auth;

import com.ted.securitypractice.dao.auth.UserRepository;
import com.ted.securitypractice.dto.auth.UserDto;
import com.ted.securitypractice.model.auth.Authority;
import com.ted.securitypractice.model.auth.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public User signup(UserDto userDto) {
		if (userRepository.findOneWithAuthoritiesByUserName(userDto.getUserName()).orElse(null) != null) {
			throw new RuntimeException("The user already exists.");
		}

		// 신규로 등록되는 user인 경우, 권한 정보를 ROLE_USER로 생성
		Authority authority = Authority.builder()
				.authorityName("ROLE_USER")
				.build();

		User user = User.builder()
				.userName(userDto.getUserName())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.nickname(userDto.getNickname())
				.authorities(Collections.singleton(authority))
				.activated(true)
				.build();

		return userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities(String userName) {
		return userRepository.findOneWithAuthoritiesByUserName(userName);
	}

	// 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
	@Transactional(readOnly = true)
	public Optional<User> getMyUserWithAuthorities() {
		return SecurityUtil.getCurrentUsername()
				.flatMap(userRepository::findOneWithAuthoritiesByUserName);
	}
}
