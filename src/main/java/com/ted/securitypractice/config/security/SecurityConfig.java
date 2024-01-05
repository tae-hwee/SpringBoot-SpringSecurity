package com.ted.securitypractice.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	// BCryptPasswordEncoder 써서 password 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// csrf는 disable
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(jwtAccessDeniedHandler)

				// jwt 기반이므로 stateless로 설정
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				.and()
				.authorizeHttpRequests()
				.requestMatchers("/api/authenticate").permitAll() // 로그인 api는 인증 없이 접근 가능
				.requestMatchers("/api/signup").permitAll() // 회원가입 api는 인증 없이 접근 가능
				.requestMatchers("/favicon.ico").permitAll()
				//.anyRequest().authenticated() // 나머지 요청은 인증 필요함 (but, 일단 임시로 주석 처리)
				.anyRequest().permitAll()       // 추후 이걸 주석 처리 하고, 윗 statement를 주석 해제

				.and()
				.apply(new JwtSecurityConfig(tokenProvider)); // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig class 적용

		return httpSecurity.build();
	}
}
