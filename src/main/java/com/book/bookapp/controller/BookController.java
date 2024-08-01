package com.book.bookapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.bookapp.dto.request.BookRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	BookService service;
	
	@PostMapping
	public AppResponse createBook(@RequestBody BookRequest request) {
		return service.addBook(request);
	}
	
	@DeleteMapping
	public AppResponse deleteBook(@RequestBody BookRequest request) {
		return service.deleteBook(request);
	}

}
