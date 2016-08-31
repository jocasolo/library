package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

@Repository
public interface BookDAO extends CrudRepository<Book, Integer> {

	@Query(value = "SELECT b from Book as b WHERE (b.isbn LIKE %?1% OR ?1 IS NULL) AND (b.title LIKE %?2% OR ?2 IS NULL) AND (b.author LIKE %?3% OR ?3 IS NULL) AND status <> 'DELETED'")
	public List<Book> search(String isbn, String title, String author);
	
	@Query(value = "SELECT b FROM Book AS b WHERE id = ?1 AND status <> 'DELETED'")
	public Book findOne(Integer id);
	
	@Query(value = "SELECT b FROM Book AS b WHERE status <> 'DELETED'")
	public List<Book> findAll();
	
}
