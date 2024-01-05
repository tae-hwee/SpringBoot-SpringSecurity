package com.ted.securitypractice.service.book;

import com.ted.securitypractice.dao.book.AuthorRepository;
import com.ted.securitypractice.dto.book.AuthorDto;
import com.ted.securitypractice.model.book.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
	private final AuthorRepository authorRepository;

	public List<String> getAllAuthorNames() {
		List<Author> authorList = authorRepository.findAll();
		List<String> authorNameList = new ArrayList<>();

		for (int i = 0; i < authorList.size(); i++) {
			authorNameList.add(i, authorList.get(i).getAuthorName());
		}

		return authorNameList;
	}

	public Author createAuthor(AuthorDto authorDto) {
		Author author = Author.builder()
				.authorName(authorDto.getAuthorName())
				.nickName(authorDto.getNickName())
				.penName(authorDto.getPenName())
				.build();

		return authorRepository.save(author);
	}
}
