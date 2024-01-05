package com.ted.securitypractice.dao.book;

import com.ted.securitypractice.model.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
