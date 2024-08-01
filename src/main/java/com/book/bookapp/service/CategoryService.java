package com.book.bookapp.service;

import java.util.List;

import com.book.bookapp.dto.request.BookRequest;
import com.book.bookapp.dto.request.CategoryRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.dto.response.BookResponse;
import com.book.bookapp.dto.response.CategoryResponse;
import com.book.bookapp.entity.Category;

public interface CategoryService {
	
	CategoryResponse findCategoryByName(String name);
	
	AppResponse createCategory(CategoryRequest request);
	
	AppResponse deleteCategory(CategoryRequest request);
	
	List<CategoryResponse> getAllCategories();
	
	List<BookResponse> getAllCategoryBooks(CategoryRequest request);
}
