package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDAO;
import com.at.library.dto.BookDTO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.model.Rent;
import com.at.library.service.book.BookService;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private RentDAO rentDao;
	
	@Autowired
	private BookService bookService;

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
	public RentDTO rentBook(BookDTO book, UserDTO user, EmployeeDTO employee) {		
		if(bookService.isAvailable(book)){
			RentDTO rent = new RentDTO();
			rent.setBook(book);
			rent.setUser(user);
			rent.setEmployee(employee);
			rent.setInitDate(new Date());
			rent.setEndDate(new Date());
			
			rentDao.save(transform(rent));
			return rent;
		}
		
		return new RentDTO();
	}

}
