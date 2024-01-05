package com.ted.securitypractice.api.auth;

import com.ted.securitypractice.config.security.JwtFilter;
import com.ted.securitypractice.config.security.TokenProvider;
import com.ted.securitypractice.dto.auth.LoginDto;
import com.ted.securitypractice.dto.auth.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@PostMapping("/authenticate")
	public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);

		HttpHeaders httpHeaders = new HttpHeaders();

		// response header와 body에 jwt token 넣어주고, body를 return
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
	}
}
