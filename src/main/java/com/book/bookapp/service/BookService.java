package com.book.bookapp.service;

import com.book.bookapp.dto.request.BookRequest;
import com.book.bookapp.dto.response.AppResponse;

public interface BookService {
	
	AppResponse addBook(BookRequest request);
	
	AppResponse deleteBook(BookRequest request);

}
