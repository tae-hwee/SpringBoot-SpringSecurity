package com.ted.securitypractice.model.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;
}
