package com.ted.securitypractice.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	@NotNull
	@Size(min = 3, max = 50)
	private String userName;

	@NotNull
	@Size(min = 3, max = 100)
	private String password;
}
