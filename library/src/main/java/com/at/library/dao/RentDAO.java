package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.Rent;

@Repository
public interface RentDAO extends CrudRepository<Rent, Integer>{
	
	public Rent findOneByPkBookAndReturnDateIsNull(Book book);
	
	@Query(value="SELECT r FROM Rent AS r WHERE r.returnDate IS NULL")
	public List<Rent> findSanctionalbe();
	
}
