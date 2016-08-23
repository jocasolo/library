package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

}
