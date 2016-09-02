package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;

@Repository
public interface BookDAO extends CrudRepository<Book, Integer> {

	@Query(value = "SELECT b from Book as b WHERE (b.isbn LIKE %:isbn% OR :isbn IS NULL) AND (b.title LIKE %:title% OR :title IS NULL) AND (b.author LIKE %:author% OR :author IS NULL) AND status <> 'DELETED'")
	public List<Book> search(@Param("isbn") String isbn, @Param("title") String title, @Param("author") String author, Pageable pageable);
	
	@Query(value = "SELECT b FROM Book AS b WHERE id = :id AND status <> 'DELETED'")
	public Book findOne(@Param("id") Integer id);
	
	@Query(value = "SELECT b FROM Book AS b WHERE status <> 'DELETED'")
	public List<Book> findAll(Pageable pageable);
	
}
