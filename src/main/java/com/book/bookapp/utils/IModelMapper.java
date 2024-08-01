package com.book.bookapp.utils;

import org.modelmapper.ModelMapper;

public interface IModelMapper {
	ModelMapper forRequest();
	ModelMapper forResponse();
}
