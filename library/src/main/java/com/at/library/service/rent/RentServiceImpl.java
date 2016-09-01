package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDAO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.enums.BookEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookRentedException;
import com.at.library.exceptions.UserBannedException;
import com.at.library.model.Book;
import com.at.library.model.Employee;
import com.at.library.model.Rent;
import com.at.library.model.User;
import com.at.library.service.book.BookService;
import com.at.library.service.employee.EmployeeService;
import com.at.library.service.user.UserService;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private RentDAO rentDao;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<RentDTO> findAll(Pageable pageable) {

		final Iterator<Rent> iterator = rentDao.findAll(pageable).iterator();
		final List<RentDTO> res = new ArrayList<>();

		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			res.add(transform(r));
		}
		return res;
	}

	@Override
	public RentDTO create(Integer idBook, RentPostDTO rentDto) throws BookNotFoundException, BookRentedException, UserBannedException {
		final Book book = bookService.findOne(idBook);

		if (bookService.isAvailable(book)) {
			final User user = userService.findOne(rentDto.getUser());
			
			if(!userService.isBanned(user)){
				final Employee employee = employeeService.findOne(rentDto.getEmployee());
	
				bookService.changeStatus(book, BookEnum.RENTED);
				Rent rent = new Rent();
				rent.setBook(book);
				rent.setUser(user);
				rent.setEmployee(employee);
				rent.setInitDate(new Date());
				rent.setEndDate(calcEndDate(new Date()));
	
				rentDao.save(rent);
				return transform(rent);
			}
			else
				throw new UserBannedException();
		}
		
		throw new BookRentedException();
	}

	@Override
	public RentDTO restore(Integer idBook) throws BookNotFoundException {
		Book book = bookService.findOne(idBook);
		Rent rent = rentDao.findOneByBookAndReturnDateIsNull(book);

		bookService.changeStatus(book, BookEnum.OK);
		rent.setReturnDate(new Date());

		rentDao.save(rent);
		return transform(rent);
	}

	@Override
	public Date calcEndDate(Date initDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(initDate);
		c.add(Calendar.DATE, 3); // Suponemos que siempre es 3 d�as despu�s.

		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			c.add(Calendar.DATE, 2);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			c.add(Calendar.DATE, 1);

		return c.getTime();
	}

	@Override
	public List<Rent> findSanctionable() {
		return rentDao.findSanctionalbe();
	}

	@Override
	public List<HistoryRentedDTO> getBookHistory(Integer idBook, Pageable pageable) {
		return transform(rentDao.findAllByBookId(idBook, pageable), HistoryRentedDTO.class);
	}

	@Override
	public List<HistoryRentedDTO> getUserHistory(Integer idUser, Pageable pageable) {
		return transform(rentDao.findAllByUserId(idUser, pageable), HistoryRentedDTO.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
	}
	
	@Override
	public <T> List<T> transform(List<Rent> rents, Class<T> destinationClass) {
		List<T> res = new ArrayList<>();
		for(Rent rent : rents)
			res.add(dozer.map(rent, destinationClass));
		return res;
	}
	
}
