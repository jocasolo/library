package com.at.library.service.rent;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.RentDAO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.enums.BookEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookNotRentedException;
import com.at.library.exceptions.BookRentedException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.UserBannedException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Book;
import com.at.library.model.Employee;
import com.at.library.model.Rent;
import com.at.library.model.User;
import com.at.library.service.CommonService;
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
	private CommonService commonService;

	@Override
	@Transactional(readOnly = true)
	public List<HistoryRentedDTO> findAll(Pageable pageable) {
		final List<Rent> rents = rentDao.findAll(pageable);
		return commonService.transform(rents, HistoryRentedDTO.class);
	}

	@Override
	public RentDTO create(RentPostDTO rentDto) throws BookRentedException, UserBannedException,
			EmployeeNotFoundException, BookNotFoundException, UserNotFoundException {

		final Book book = bookService.findOne(rentDto.getBook());
		if (!bookService.isAvailable(book))
			throw new BookRentedException();

		final User user = userService.findOne(rentDto.getUser());
		if (userService.isBanned(user))
			throw new UserBannedException();

		final Employee employee = employeeService.findOne(rentDto.getEmployee());

		bookService.changeStatus(book, BookEnum.RENTED);
		Rent rent = new Rent();
		rent.setBook(book);
		rent.setUser(user);
		rent.setEmployee(employee);
		rent.setInitDate(new Date());
		rent.setReturnDate(calcReturnDate(new Date()));

		rentDao.save(rent);
		return commonService.transform(rent, RentDTO.class);
	}

	@Override
	public RentDTO restore(Integer idBook) throws BookNotFoundException, BookNotRentedException {
		Book book = bookService.findOne(idBook);
		Rent rent = rentDao.findOneByBookAndEndDateIsNull(book);
		if (rent == null)
			throw new BookNotRentedException();

		bookService.changeStatus(book, BookEnum.OK);
		rent.setEndDate(new Date());

		rentDao.save(rent);
		return commonService.transform(rent, RentDTO.class);
	}

	@Override
	public Date calcReturnDate(Date initDate) {

		DateTime returnDate = new DateTime(initDate);
		returnDate = returnDate.plusDays(3);

		if (returnDate.getDayOfWeek() == DateTimeConstants.SATURDAY)
			returnDate = returnDate.plusDays(2);
		if (returnDate.getDayOfWeek() == DateTimeConstants.SUNDAY)
			returnDate = returnDate.plusDays(1);

		return returnDate.toDate();
	}

	@Override
	public List<Rent> findSanctionable() {
		return rentDao.findSanctionalbe();
	}

	@Override
	public List<HistoryRentedDTO> getBookHistory(Integer idBook, Pageable pageable) {
		return commonService.transform(rentDao.findAllByBookId(idBook, pageable), HistoryRentedDTO.class);
	}

	@Override
	public List<HistoryRentedDTO> getUserHistory(Integer idUser, Pageable pageable) {
		return commonService.transform(rentDao.findAllByUserId(idUser, pageable), HistoryRentedDTO.class);
	}

}
