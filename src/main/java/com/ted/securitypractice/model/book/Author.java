package com.ted.securitypractice.model.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "author")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {
	@JsonIgnore
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "authorName", length = 50, nullable = false)
	private String authorName;

	@Column(name = "penName", length = 50)
	private String penName;

	@Column(name = "nickName", length = 50)
	private String nickName;
}
