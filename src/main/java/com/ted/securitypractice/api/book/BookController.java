package com.ted.securitypractice.api.book;

import com.ted.securitypractice.dto.book.AuthorDto;
import com.ted.securitypractice.model.book.Author;
import com.ted.securitypractice.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookController {
	private final BookService bookService;

	@GetMapping("/author")
	public ResponseEntity<List<String>> getAllAuthorNames() {
		return ResponseEntity.ok().body(bookService.getAllAuthorNames());
	}

	@PostMapping("/author")
	public ResponseEntity<Author> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
		return ResponseEntity.ok(bookService.createAuthor(authorDto));
	}
}
