package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.controller.BookController;
import com.at.library.dao.UserDAO;
import com.at.library.dto.UserDTO;
import com.at.library.dto.UserPutDTO;
import com.at.library.model.User;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);;
	
	@Autowired
	private UserDAO userDao;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	@Scheduled(cron = "15 0/1 * * * ?")
	public void penalize(){
		log.debug("Comienza el proceso de penalización de usuarios.");
	}
	
	@Override
	@Scheduled(cron = "45 0/1 * * * ?")
	public void forgive(){
		log.debug("Comienza el proceso de comprobación de sanciones a usuarios.");
	}

	@Override
	public List<UserDTO> findAll() {
		final Iterator<User> iterator = userDao.findAll().iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User r = iterator.next();
			res.add(transform(r));
		}
		return res;
	}

	@Override
	public User findOne(Integer id) {
		final User user = userDao.findOne(id);
		return user;
	}

	@Override
	public UserDTO create(UserDTO userDto) {
		final User user = transform(userDto);
		return transform(userDao.save(user));
	}

	@Override
	public void update(UserPutDTO userDto) {
		User user = transform(userDto);
		userDao.save(user);
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}

	@Override
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public <T> User transform(T userDto) {
		return dozer.map(userDto, User.class);
	}

}
