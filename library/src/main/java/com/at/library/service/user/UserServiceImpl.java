package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDAO;
import com.at.library.dto.UserDTO;
import com.at.library.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<UserDTO> findAll() {
		final Iterator<User> iterator = userDao.findAll().iterator();
		final List<UserDTO> res = new ArrayList<>();

		while (iterator.hasNext()) {
			final User u = iterator.next();
			res.add(transform(u));
		}
		return res;
	}

	@Override
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public User transform(UserDTO user) {
		return dozer.map(user, User.class);
	}

}
