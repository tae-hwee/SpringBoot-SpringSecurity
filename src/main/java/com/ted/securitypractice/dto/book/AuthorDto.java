package com.ted.securitypractice.dto.book;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
	@NotNull
	private Long id;

	private String authorName;

	private String penName;

	private String nickName;

}
