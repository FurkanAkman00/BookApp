package com.book.bookapp.dto.response;

import java.util.List;

import com.book.bookapp.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
	
	private String name;
	
	private List<Book> books;

}
