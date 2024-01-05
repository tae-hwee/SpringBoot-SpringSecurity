package com.ted.securitypractice.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@JsonIgnore
	@Id // primary key
	@Column(name = "userId")
	// 자동 증가 되는
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "userName", length = 50, unique = true)
	private String userName;

	@JsonIgnore
	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "nickname", length = 50)
	private String nickname;

	@JsonIgnore
	@Column(name = "activated")
	private boolean activated;

	@ManyToMany
	@JoinTable(
			name = "user_authority",
			joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId")},
			inverseJoinColumns = {@JoinColumn(name = "authorityName", referencedColumnName = "authorityName")})
	private Set<Authority> authorities;
}
