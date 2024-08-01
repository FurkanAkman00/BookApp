package com.book.bookapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.bookapp.dto.request.BookRequest;
import com.book.bookapp.dto.request.CategoryRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.dto.response.BookResponse;
import com.book.bookapp.dto.response.CategoryResponse;
import com.book.bookapp.entity.Book;
import com.book.bookapp.entity.Category;
import com.book.bookapp.repository.BookRepository;
import com.book.bookapp.repository.CategoryRepository;
import com.book.bookapp.service.CategoryService;
import com.book.bookapp.utils.IModelMapper;


@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private IModelMapper modelMapper;



	@Override
	public AppResponse createCategory(CategoryRequest request) {
		if(repository.findByName(request.getName()) != null) {
			return AppResponse.builder()
					.responseMessage("Category Allready Exists!")
					.status("Fail")
					.build();
		}
		
		Category category = Category.builder()
				.name(request.getName())
				.books(null)
				.build();
		
		repository.save(category);
		
		return AppResponse.builder()
				.status("Success")
				.responseMessage("Category Created!")
				.build();
	}

	@Override
	public AppResponse deleteCategory(CategoryRequest request) {
		Category category = repository.findByName(request.getName());
		if(category == null) {
			return AppResponse.builder()
					.status("Fail")
					.responseMessage("Category Not Found!")
					.build();
		}
		repository.delete(category);
		return AppResponse.builder()
				.status("Success")
				.responseMessage("Category Deleted!")
				.build();
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		List<Category> categories = repository.findAll();
		
		List<CategoryResponse> categoriesResponse = categories.stream().map
				(category -> modelMapper.forResponse().map(category, CategoryResponse.class)).toList();
		
		return categoriesResponse;
	}

	@Override
	public List<BookResponse> getAllCategoryBooks(CategoryRequest request) {
		Category category = repository.findByName(request.getName());
		
		List<BookResponse> responseBooks = category.getBooks().stream().map(book -> modelMapper.forResponse().map(book,BookResponse.class)).toList();
		
		return responseBooks;
	}

	@Override
	public CategoryResponse findCategoryByName(String name) {
		Category category = repository.findByName(name);
		return CategoryResponse.builder()
				.name(category.getName())
				.books(category.getBooks())
				.build();

	}
	
	
}
