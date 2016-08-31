package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDAO;
import com.at.library.dto.UserDTO;
import com.at.library.dto.UserPutDTO;
import com.at.library.enums.UserEnum;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Rent;
import com.at.library.model.User;
import com.at.library.service.rent.RentService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDao;

	@Autowired
	private RentService rentService;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	@Scheduled(cron = "0 0 2 1/1 * ?")
	@Transactional
	public void penalize() {
		log.debug("Comienza el proceso de penalización de usuarios.");
		Iterator<Rent> iterator = rentService.findSanctionable().iterator();
		while (iterator.hasNext()) {
			final Rent rent = iterator.next();
			final User user = rent.getUser();
			
			// Dias que se ha retrasado multiplicado por 3 = dias de sanción
			final Days days = Days.daysBetween(new DateTime(rent.getEndDate()), new DateTime());
			days.multipliedBy(3);

			DateTime initDate = (user.getPenalizeDate() != null) ? new DateTime(user.getPenalizeDate()) : new DateTime();
						
			// Añadimos los dias de castigo
			final DateTime forgiveDate = initDate;
			forgiveDate.plus(days);

			user.setStatus(UserEnum.BANNED);
			user.setPenalizeDate(initDate.toDate());
			user.setForgiveDate(forgiveDate.toDate());

			userDao.save(user);
			log.debug("Usuario %s sancionado %s dias.", user, days);
		}
	}

	@Override
	@Scheduled(cron = "0 0 3 1/1 * ?")
	public void forgive() {
		log.debug("Comienza el proceso de comprobación de sanciones a usuarios.");
		Iterator<User> iterator = userDao.findByStatus(UserEnum.BANNED).iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getForgiveDate().before(new Date())){
				user.setStatus(UserEnum.NORMAL);
				user.setPenalizeDate(null);
				user.setForgiveDate(null);
				userDao.save(user);
				log.debug("Usuario %s perdonado", user);
			}
		}
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
		user.setStatus(UserEnum.NORMAL);
		return transform(userDao.save(user));
	}

	@Override
	public void update(UserPutDTO userDto) {
		User user = transform(userDto);
		userDao.save(user);
	}

	@Override
	public void delete(Integer id) throws UserNotFoundException {
		final User user = userDao.findOne(id);
		if(user == null)
			throw new UserNotFoundException();
		user.setStatus(UserEnum.DELETED);
		userDao.save(user);
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

	@Override
	public List<UserDTO> search(String dni, String name, String surname) {
		return userDao.search(dni, name, surname);
	}

	@Override
	public Boolean isBanned(User user) {
		return !user.getStatus().equals(UserEnum.NORMAL);
	}

}
