package com.book.bookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.bookapp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findByName(String name);

}
