package com.at.library.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.enums.UserEnum;
import com.at.library.model.User;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {
	
	public List<User> findByStatus(UserEnum status);
	
}
