package com.book.bookapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddDeleteBookRequest {
	
	private String userEmail;
	
	private String bookName;

}
