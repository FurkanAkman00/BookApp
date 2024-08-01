package com.book.bookapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AppResponse {
	
	private String status;
	
	private String responseMessage;

}
