package com.at.library.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.dto.UserDTO;
import com.at.library.enums.UserEnum;
import com.at.library.model.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

	public List<User> findByStatus(UserEnum status);

	@Query(value = "SELECT new com.at.library.dto.UserDTO(u.id, u.dni, u.name, u.surname) from User as u WHERE (u.dni LIKE %:dni% OR :dni IS NULL) AND (u.name LIKE %:name% OR :name IS NULL) AND (u.surname LIKE %:surname% OR :surname IS NULL) AND u.status <> 'DELETED'")
	public List<UserDTO> search(@Param("dni") String dni, @Param("name") String name, @Param("surname") String surname, Pageable pageable);
	
	@Query(value = "SELECT u FROM User AS u WHERE u.id = :id AND u.status <> 'DELETED'")
	public User findOne(@Param("id") Integer id);
	
	@Query(value = "SELECT u FROM User AS u WHERE u.status <> 'DELETED'")
	public List<User> findAll(Pageable pageable);
	
}
