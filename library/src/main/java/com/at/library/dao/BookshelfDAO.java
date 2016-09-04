package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.at.library.model.Bookshelf;

public interface BookshelfDAO extends CrudRepository<Bookshelf, Integer> {

	@Query(value = "SELECT b FROM Bookshelf AS b")
	public List<Bookshelf> findAll(Pageable pageable);

	@Query(value = "SELECT b FROM Bookshelf AS b WHERE b.code = :code")
	public Bookshelf findOne(@Param("code") String code);

}
