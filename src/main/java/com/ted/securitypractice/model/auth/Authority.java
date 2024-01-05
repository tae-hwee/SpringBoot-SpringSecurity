package com.ted.securitypractice.model.auth;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
	@Id
	@Column(name = "authorityName", length = 50)
	private String authorityName;
}
