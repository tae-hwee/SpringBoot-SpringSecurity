package com.ted.securitypractice.service.auth;

import com.ted.securitypractice.dao.auth.UserRepository;
import com.ted.securitypractice.model.auth.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	@Transactional
	// 로그인 시 DB에서 가져온 user 정보와 권한 정보를 기반으로 userdetails.User 객체를 생성해 리턴
	public UserDetails loadUserByUsername(final String userName) {
		return userRepository.findOneWithAuthoritiesByUserName(userName)
				.map(user -> createUser(userName, user))
				.orElseThrow(() -> new UsernameNotFoundException(userName + " -> does not exist in database."));
	}

	private org.springframework.security.core.userdetails.User createUser(String userName, User user) {
		if (!user.isActivated()) {
			throw new RuntimeException(userName + " -> is not active.");
		}

		List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}
}
