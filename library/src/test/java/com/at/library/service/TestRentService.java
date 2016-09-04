package com.at.library.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import com.at.library.dao.BookDAO;
import com.at.library.dao.RentDAO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.enums.BookEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookNotRentedException;
import com.at.library.model.Book;
import com.at.library.model.Rent;
import com.at.library.service.book.BookService;
import com.at.library.service.rent.RentService;
import com.at.library.service.rent.RentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestRentService {

	private static final Date INIT = new Date();

	private Rent rent = new Rent();
	private Rent rSanction = new Rent();
	private Book book = new Book();
	private List<HistoryRentedDTO> huserRents = new ArrayList<>();

	@InjectMocks
	private RentService rentService = new RentServiceImpl();

	@Mock
	private DozerBeanMapper dozer;

	@Mock
	private RentDAO rentDao;
	
	@Mock
	private BookDAO bookDao;

	@Mock
	private BookService bookService;
	
	@Mock
	private CommonService commonService;

	@Before
	public void init() throws BookNotFoundException {
		final RentDTO r = new RentDTO();
		r.setInitDate(INIT);
		book.setStatus(BookEnum.OK);
		rent.setBook(book);
		rSanction.setReturnDate(new DateTime("2016-01-01").toDate());

		Mockito.when(rentDao.findOne(1)).thenReturn(rent);
		Mockito.when(rentDao.findOne(100)).thenReturn(null);
		Mockito.when(rentDao.save(rent)).thenReturn(rent);
		Mockito.when(rentDao.findSanctionalbe()).thenReturn(Arrays.asList(rSanction));
		Mockito.when(rentDao.findOneByBookAndEndDateIsNull(book)).thenReturn(rent);
		Mockito.when(bookService.findOne(1)).thenReturn(book);
		Mockito.when(bookDao.findOne(1)).thenReturn(book);
		Mockito.when(rentService.getUserHistory(null, null)).thenReturn(huserRents);
	}

	@Test
	public void testCalcReturnDate() {
		DateTime expectedDate = new DateTime("2016-01-04");
		Date actualDate = rentService.calcReturnDate(new DateTime("2016-01-01").toDate());
		Assert.assertEquals(expectedDate.toDate(), actualDate);

		expectedDate = new DateTime("2016-01-11");
		actualDate = rentService.calcReturnDate(new DateTime("2016-01-07").toDate());
		Assert.assertEquals(expectedDate.toDate(), actualDate);

		expectedDate = new DateTime("2016-01-11");
		actualDate = rentService.calcReturnDate(new DateTime("2016-01-06").toDate());
		Assert.assertEquals(expectedDate.toDate(), actualDate);
	}

	@Test
	public void testFindSanctionable() {
		final List<Rent> res = rentService.findSanctionable();
		Assert.assertEquals(null, res.get(0).getEndDate());
		Assert.assertEquals(new DateTime("2016-01-01").toDate(), res.get(0).getReturnDate());
	}

	@Test
	public void testRestore() throws BookNotFoundException, BookNotRentedException {
		book.setStatus(BookEnum.RENTED);
		rentService.restore(1);
		Assert.assertNotNull(rent.getEndDate());
		final Days days = Days.daysBetween(new DateTime(rent.getEndDate()), new DateTime());
		Assert.assertEquals(0, days.getDays());
	}

	@Test(expected = BookNotRentedException.class)
	public void testErrorRestore() throws BookNotFoundException, BookNotRentedException {
		book.setStatus(BookEnum.OK);
		rentService.restore(100);
	}
	
	@Test
	public void testGetHistoryUser() {
		createHistoryUser();
		List<HistoryRentedDTO> hRents = rentService.getUserHistory(1, new PageRequest(0, 10));
		Assert.assertEquals(2, hRents.size());
		Assert.assertEquals(2, hRents.get(0).getIdBook().intValue());
		Assert.assertEquals(3, hRents.get(1).getIdBook().intValue());
	}

	private void createHistoryUser(){
		HistoryRentedDTO h1 = new HistoryRentedDTO();
		h1.setIdBook(2);
		h1.setInit(new DateTime("2016-01-10").toDate());
		HistoryRentedDTO h2 = new HistoryRentedDTO();
		h2.setIdBook(3);
		h2.setInit(new DateTime("2016-01-15").toDate());
		huserRents.add(h1);
		huserRents.add(h2);
	}

}
