package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.UserDAO;
import com.at.library.dto.UserDTO;
import com.at.library.dto.UserPostDTO;
import com.at.library.dto.UserPutDTO;
import com.at.library.enums.UserEnum;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.exceptions.UserWrongUpdateException;
import com.at.library.model.Rent;
import com.at.library.model.User;
import com.at.library.service.CommonService;
import com.at.library.service.rent.RentService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDao;

	@Autowired
	private RentService rentService;

	@Autowired
	private CommonService commonService;

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		final Iterator<User> iterator = userDao.findAll().iterator();
		final List<UserDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final User r = iterator.next();
			res.add(commonService.transform(r, UserDTO.class));
		}
		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(Integer id) throws UserNotFoundException {
		final User user = userDao.findOne(id);
		if (user == null)
			throw new UserNotFoundException();
		return user;
	}

	@Override
	public UserDTO create(UserPostDTO userDto) {
		User user = commonService.transform(userDto, User.class);
		user.setStatus(UserEnum.NORMAL);
		return commonService.transform(userDao.save(user), UserDTO.class);
	}

	@Override
	public void update(Integer id, UserPutDTO userDto) throws UserWrongUpdateException {
		if (userDto.getId() != null && id != userDto.getId())
			throw new UserWrongUpdateException();
		User u = userDao.findOne(id);
		u.setDni(userDto.getDni());
		u.setName(userDto.getName());
		u.setSurname(userDto.getSurname());
		userDao.save(u);
	}

	@Override
	public void delete(Integer id) throws UserNotFoundException {
		final User user = findOne(id);
		user.setStatus(UserEnum.DELETED);
		userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> search(String dni, String name, String surname, Pageable pageable) {
		return userDao.search(dni, name, surname, pageable);
	}

	@Override
	public Boolean isBanned(User user) {
		return !user.getStatus().equals(UserEnum.NORMAL);
	}

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
			Days days = Days.daysBetween(new DateTime(rent.getReturnDate()), new DateTime());
			days = days.multipliedBy(3);

			DateTime initDate = (user.getPenalizeDate() != null) ? new DateTime(user.getPenalizeDate())
					: new DateTime();

			// Añadimos los dias de castigo
			DateTime forgiveDate = initDate;
			forgiveDate = forgiveDate.plus(days);

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
			if (user.getForgiveDate().before(new Date())) {
				user.setStatus(UserEnum.NORMAL);
				user.setPenalizeDate(null);
				user.setForgiveDate(null);
				userDao.save(user);
				log.debug("Usuario %s perdonado", user);
			}
		}
	}

}
