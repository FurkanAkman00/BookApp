package com.book.bookapp.service;

import java.util.List;

import com.book.bookapp.dto.request.BookRequest;
import com.book.bookapp.dto.request.UserAddDeleteBookRequest;
import com.book.bookapp.dto.request.UserRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.dto.response.BookResponse;
import com.book.bookapp.entity.User;

public interface UserService {
	
	AppResponse createUser(UserRequest request);
	
	AppResponse addBookToList(UserAddDeleteBookRequest request);
	
	AppResponse deleteUser(UserRequest request);
	
	User findUserByEmail(String email);
	
	List<BookResponse> getAllListedBooks(String email);
	
	AppResponse deleteBookFromList(UserAddDeleteBookRequest request);
}
