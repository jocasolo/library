package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDAO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.enums.StatusEnum;
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
	public List<RentDTO> findAll() {
		final Iterator<Rent> iterator = rentDao.findAll().iterator();
		final List<RentDTO> res = new ArrayList<>();
		
		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			res.add(transform(r));
		}
		return res;
	}

	@Override
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
	}

	@Override
	public Rent transform(RentDTO rent) {
		return dozer.map(rent, Rent.class);
	}

	@Override
	public RentDTO create(RentPostDTO rentDto) {
		
		final Book book = bookService.findOne(rentDto.getIdBook());
		
		if(bookService.isAvailable(book)){
			final User user = userService.findOne(rentDto.getIdUser());
			final Employee employee = employeeService.findOne(rentDto.getIdEmployee());
			
			bookService.changeStatus(book, StatusEnum.DISABLE);
			
			Rent rent = new Rent();
			rent.setBook(book);
			rent.setUser(user);
			rent.setEmployee(employee);
			rent.setInitDate(new Date());
			rent.setEndDate(calcEndDate(new Date()));
			
			rentDao.save(rent);
			return transform(rent);
		}
		
		return new RentDTO();
	}

	@Override
	public Date calcEndDate(Date initDate) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(initDate); 
		c.add(Calendar.DATE, 3); // Suponemos que siempre es 3 días después.
		
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			c.add(Calendar.DATE, 2);
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			c.add(Calendar.DATE, 1);
		
		return c.getTime();
	}
	
	

}
