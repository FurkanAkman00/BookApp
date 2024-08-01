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

import com.book.bookapp.dto.request.CategoryRequest;
import com.book.bookapp.dto.response.AppResponse;
import com.book.bookapp.dto.response.BookResponse;
import com.book.bookapp.dto.response.CategoryResponse;
import com.book.bookapp.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	@PostMapping
	public AppResponse createCategory(@RequestBody CategoryRequest request) {
		return service.createCategory(request);
	}
	
	@DeleteMapping
	public AppResponse deleteCategory(@RequestBody CategoryRequest request) {
		return service.deleteCategory(request);
	}
	
	@GetMapping
	public CategoryResponse getCategoryByName(@RequestParam CategoryRequest request) {
		return service.findCategoryByName(request.getName());
	}
	
	@GetMapping("/all")
	public List<CategoryResponse> getAllCategories(){
		return service.getAllCategories();
	}
	
	@GetMapping("/books")
	public List<BookResponse> getAllCategoryBooks(@RequestParam CategoryRequest request){
		return service.getAllCategoryBooks(request);
	}

}
