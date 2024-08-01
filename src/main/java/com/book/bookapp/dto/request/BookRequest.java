package com.book.bookapp.dto.request;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
	private String name;
	
	private String author;
	
	private Date creationDate;
	
	private String category;

}
