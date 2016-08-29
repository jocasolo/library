package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Book;

@Repository
public interface BookDAO extends CrudRepository<Book, Integer> {

	@Query(value = "SELECT new com.at.library.dto.BookDTO(b.id, b.isbn, b.title, b.author) from Book as b WHERE b.id IN (SELECT r.pk.book.id FROM Rent as r WHERE r.returnDate IS NULL)")
	public List<BookDTO> findNoAvailable();

	public List<BookDTO> findByIsbnOrTitleOrAuthorOrStatusAllIgnoreCase(String isbn, String title, String author,
			StatusEnum status);

}
