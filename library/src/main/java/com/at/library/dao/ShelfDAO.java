package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;

import com.at.library.model.Bookshelf;

public interface ShelfDAO extends CrudRepository<Bookshelf, Integer> {
	
}
