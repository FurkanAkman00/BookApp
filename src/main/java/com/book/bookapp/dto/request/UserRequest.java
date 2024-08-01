package com.book.bookapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;

}
