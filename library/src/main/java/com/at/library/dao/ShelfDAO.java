package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;

import com.at.library.model.Shelf;

public interface ShelfDAO extends CrudRepository<Shelf, Integer> {
	
}
