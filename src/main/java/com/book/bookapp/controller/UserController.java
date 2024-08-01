package com.book.bookapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.bookapp.dto.request.BookRequest;
import com.book.bookapp.dto.request.UserAddDeleteBookRequest;
import com.book.bookapp.dto.request.UserRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.dto.response.BookResponse;
import com.book.bookapp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping
	public AppResponse createUser(@RequestBody UserRequest request) {
		return service.createUser(request);
		
	}
	
	@DeleteMapping
	public AppResponse deleteUser(@RequestBody UserRequest request) {
		return service.deleteUser(request);
	}
	
	@PostMapping("/addbook")
	public AppResponse addBookToUserList(@RequestBody UserAddDeleteBookRequest request) {
		return service.addBookToList(request);
	}
	
	@GetMapping("/books")
	public List<BookResponse> getAllListedBooks(@RequestParam String email){
		return service.getAllListedBooks(email);
		
	}	
	
	@PostMapping("/deletebook")
	public AppResponse deleteBookFromList (@RequestBody UserAddDeleteBookRequest request) {
		return service.deleteBookFromList(request);
	}

}
