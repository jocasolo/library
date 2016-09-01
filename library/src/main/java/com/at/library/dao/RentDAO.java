package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.Rent;

@Repository
public interface RentDAO extends CrudRepository<Rent, Integer>{
	
	public Rent findOneByBookAndReturnDateIsNull(Book book);
	
	@Query(value="SELECT r FROM Rent AS r WHERE r.returnDate IS NULL AND r.endDate < CURRENT_DATE")
	public List<Rent> findSanctionalbe();
	
	@Query(value="SELECT r FROM Rent AS r WHERE r.book.id = ?1")
	public List<Rent> findAllByBookId(Integer id, Pageable pageable);
	
	@Query(value="SELECT r FROM Rent AS r WHERE r.user.id = ?1")
	public List<Rent> findAllByUserId(Integer id, Pageable pageable);
	
	@Query(value="SELECT r FROM Rent AS r")
	public List<Rent> findAll(Pageable pageable);
	
}
