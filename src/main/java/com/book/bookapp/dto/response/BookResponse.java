package com.book.bookapp.dto.response;

import java.sql.Date;

import com.book.bookapp.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
	
	private String name;
	
	private String author;
	
	private Date creationDate;

}
