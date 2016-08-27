package com.at.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.Rent;

@Repository
public interface RentDAO extends CrudRepository<Rent, Integer>{
	
	public List<Rent> findByPkBookAndReturnDateIsNull(Book book);
	
}
