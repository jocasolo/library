package com.at.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.dto.UserDTO;
import com.at.library.enums.UserEnum;
import com.at.library.model.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

	public List<User> findByStatus(UserEnum status);

	@Query(value = "SELECT new com.at.library.dto.UserDTO(u.id, u.dni, u.name, u.surname) from User as u WHERE (u.dni LIKE %?1% OR ?1 IS NULL) AND (u.name LIKE %?2% OR ?2 IS NULL) AND (u.surname LIKE %?3% OR ?3 IS NULL) AND status <> 'DELETED'")
	public List<UserDTO> search(String dni, String name, String surname);
	
	@Query(value = "SELECT b FROM Book AS b WHERE id = ?1 AND status <> 'DELETED'")
	public User findOne(Integer id);
	
	@Query(value = "SELECT b FROM Book AS b WHERE status <> 'DELETED'")
	public List<User> findAll();
	
}
