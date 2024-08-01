package com.book.bookapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="User")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="listed_book",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="book_id"))
	private List<Book> listedBooks;
	
	public void addBookToList(Book book) {
		if(listedBooks.isEmpty()) {
			listedBooks = new ArrayList<Book>();
		}
		
		listedBooks.add(book);
	}
	
	public void deleteBookFromList(Book book) {
		if(listedBooks.contains(book)) {
			listedBooks.remove(book);
		}
	}

}
