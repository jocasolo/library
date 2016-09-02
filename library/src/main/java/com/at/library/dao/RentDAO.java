package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.Rent;

@Repository
public interface RentDAO extends CrudRepository<Rent, Integer>{
	
	public Rent findOneByBookAndReturnDateIsNull(Book book);
	
	@Query(value="SELECT r FROM Rent AS r WHERE r.returnDate IS NULL AND r.endDate < CURRENT_DATE")
	public List<Rent> findSanctionalbe();
	
	@Query(value="SELECT r FROM Rent AS r WHERE r.book.id = :id")
	public List<Rent> findAllByBookId(@Param("id") Integer id, Pageable pageable);
	
	@Query(value="SELECT r FROM Rent AS r WHERE r.user.id = :id")
	public List<Rent> findAllByUserId(@Param("id") Integer id, Pageable pageable);
	
	@Query(value="SELECT r FROM Rent AS r")
	public List<Rent> findAll(Pageable pageable);
	
}
