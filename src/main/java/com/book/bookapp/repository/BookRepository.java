package com.book.bookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.bookapp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	Book findByName(String name);

}
