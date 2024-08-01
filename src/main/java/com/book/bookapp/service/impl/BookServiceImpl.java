package com.book.bookapp.service.impl;

import org.springframework.stereotype.Service;

import com.book.bookapp.dto.request.BookRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.entity.Book;
import com.book.bookapp.entity.Category;
import com.book.bookapp.repository.BookRepository;
import com.book.bookapp.repository.CategoryRepository;
import com.book.bookapp.service.BookService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
	
	private BookRepository repository;
	
	private CategoryRepository categoryRepository;

	@Override
	public AppResponse addBook(BookRequest request) {
		Category category = categoryRepository.findByName(request.getCategory());
		
		Book book = Book.builder()
				.author(request.getAuthor())
				.creationDate(request.getCreationDate())
				.name(request.getName())
				.build();
		
		category.addBook(book);
		
		repository.save(book);
		categoryRepository.save(category);
		
		return AppResponse.builder()
				.status("Success")
				.responseMessage("Book Created")
				.build();
	}

	@Override
	public AppResponse deleteBook(BookRequest request) {
		Book book = repository.findByName(request.getName());
		
		repository.delete(book);
		
		return AppResponse.builder()
				.status("Success")
				.responseMessage("Book Deleted")
				.build();
	}

}
