package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.controller.UserController;
import com.at.library.dao.UserDAO;
import com.at.library.dto.UserDTO;
import com.at.library.dto.UserPutDTO;
import com.at.library.enums.UserStatus;
import com.at.library.model.Rent;
import com.at.library.model.User;
import com.at.library.service.rent.RentService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDAO userDao;

	@Autowired
	private RentService rentService;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	@Scheduled(cron = "15 0/1 * * * ?")
	@Transactional
	public void penalize() {
		log.debug("Comienza el proceso de penalización de usuarios.");
		Iterator<Rent> iterator = rentService.findSanctionable().iterator();
		while (iterator.hasNext()) {
			final Rent rent = iterator.next();
			final User user = rent.getUser();
			final Long diference = (new Date().getTime() - rent.getEndDate().getTime()) / (1000 * 60 * 60 * 24);
			final Integer days = diference.intValue() * 3;

			Date initDate = user.getPenalizeDate();
			if (initDate == null)
				initDate = new Date();

			Calendar forgiveDate = Calendar.getInstance();
			forgiveDate.setTime(initDate);
			forgiveDate.add(Calendar.DATE, days); // Calcula la fecha de perdón

			user.setStatus(UserStatus.BANNED);
			user.setPenalizeDate(initDate);
			user.setForgiveDate(forgiveDate.getTime());

			userDao.save(user);
			log.debug("Usuario %s sancionado %s dias.", user, days);
		}
	}

	@Override
	@Scheduled(cron = "45 0/1 * * * ?")
	public void forgive() {
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
		User user = transform(userDto);
		user.setStatus(UserStatus.NORMAL);
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

	@Override
	public List<UserDTO> transform(List<User> users) {
		List<UserDTO> res = new ArrayList<>();
		for (User u : users)
			res.add(transform(u));
		return res;
	}

}
