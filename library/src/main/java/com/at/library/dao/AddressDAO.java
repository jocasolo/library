package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Address;

@Repository
public interface AddressDAO extends CrudRepository<Address, Integer> {

}
