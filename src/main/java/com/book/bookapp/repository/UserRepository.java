package com.book.bookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.bookapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findUserByEmail(String email);

}
