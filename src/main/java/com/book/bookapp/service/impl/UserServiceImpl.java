package com.book.bookapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.bookapp.dto.request.BookRequest;
import com.book.bookapp.dto.request.UserAddDeleteBookRequest;
import com.book.bookapp.dto.request.UserRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.dto.response.BookResponse;
import com.book.bookapp.dto.response.CategoryResponse;
import com.book.bookapp.entity.Book;
import com.book.bookapp.entity.User;
import com.book.bookapp.repository.BookRepository;
import com.book.bookapp.repository.UserRepository;
import com.book.bookapp.service.UserService;
import com.book.bookapp.utils.IModelMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	UserRepository repository;
	BookRepository bookRepository;
	IModelMapper mapper;

	@Override
	public AppResponse createUser(UserRequest request) {
		User user = repository.findUserByEmail(request.getEmail());
		
		if(user != null) {
			return AppResponse.builder()
					.status("Fail")
					.responseMessage("User Allready exists!")
					.build();
		}
		
		User createdUser = User.builder()
				.email(request.getEmail())
				.password(request.getPassword())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.listedBooks(null)
				.build();
		
		repository.save(createdUser);
		
		return AppResponse.builder()
				.status("Success")
				.responseMessage("User Created")
				.build();
		
	}
	
	@Override
	public AppResponse deleteUser(UserRequest request) {
		User user = repository.findUserByEmail(request.getEmail());
		
		repository.delete(user);
		
		return AppResponse.builder()
				.status("Success")
				.responseMessage("User Deleted Successfully!")
				.build();
	}


	@Override
	public AppResponse addBookToList(UserAddDeleteBookRequest request) { 
		User user = repository.findUserByEmail(request.getUserEmail());
		
		Book book = bookRepository.findByName(request.getBookName());
		
		user.addBookToList(book);
		
		repository.save(user);
		
		return AppResponse.builder()
				.status("Success")
				.responseMessage("Book Added Successfully!")
				.build();
	}

	@Override
	public User findUserByEmail(String email) {
		return findUserByEmail(email);
	}

	@Override
	public List<BookResponse> getAllListedBooks(String email) {
		User user = repository.findUserByEmail(email);
		
		List<Book> books = user.getListedBooks();
		
		List<BookResponse> userBooksResponse = books.stream().map
				(book -> mapper.forResponse().map(book, BookResponse.class)).toList();
		
		return userBooksResponse;
	}

	@Override
	public AppResponse deleteBookFromList(UserAddDeleteBookRequest request) {
		Book book = bookRepository.findByName(request.getBookName());
		User user = repository.findUserByEmail(request.getUserEmail());
		
		user.deleteBookFromList(book);
		
		repository.save(user);
		
		return AppResponse.builder()
				.status("Success")
				.responseMessage("Book Deleted Successfully!")
				.build();
		
		
	}

}
