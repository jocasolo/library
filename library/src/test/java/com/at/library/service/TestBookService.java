package com.at.library.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;

import com.at.library.dao.BookDAO;
import com.at.library.dto.BookDTO;
import com.at.library.enums.BookEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.model.Book;
import com.at.library.service.book.BookService;
import com.at.library.service.book.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestBookService {

	@InjectMocks
	private BookService bookService = new BookServiceImpl();
	@Mock
	private BookDAO bookDao;
	@Mock
	private RestTemplate restTemplate = new RestTemplate();
	@Mock
	private CommonService commonService = new CommonServiceImpl();

	private List<Book> books = new ArrayList<>();
	private List<Book> books2 = new ArrayList<>();
	private List<Book> books3 = new ArrayList<>();
	private Book book = new Book();
	private Book book2 = new Book();
	private BookDTO b1 = new BookDTO();
	private BookDTO b2 = new BookDTO();
	private BookDTO b3 = new BookDTO();
	private static final String AUTHOR = "Test Autor";
	private static final String DESCRIPTION = "Test Descripción";
	private static final Integer ID = 1;
	private static final String IMAGE = "Test Imagen";
	private static final String ISBN = "1111";
	private static final String TITLE = "Test Título";
	private static final Integer YEAR = 2000;

	@Before
	public void init() {
		b1.setAuthor(AUTHOR);
		
		b2.setAuthor(AUTHOR);
		b2.setDescription(DESCRIPTION);
		b2.setId(ID);
		b2.setImage(IMAGE);
		b2.setIsbn(ISBN);
		b2.setStatus(BookEnum.OK.toString());
		b2.setTitle(TITLE);
		b2.setYear(YEAR);
		
		book.setAuthor(AUTHOR);
		book.setDescription(DESCRIPTION);
		book.setId(ID);
		book.setImage(IMAGE);
		book.setIsbn(ISBN);
		book.setStatus(BookEnum.OK);
		book.setTitle(TITLE);
		book.setYear(YEAR);
		
		book2.setAuthor(AUTHOR);

		final List<BookDTO> booksDto = new ArrayList<>();
		booksDto.add(b1);
		booksDto.add(b2);
		booksDto.add(b3);
		
		books2.add(book2);
		books3.add(book);

		Mockito.when(bookDao.search(null, TITLE, null, null)).thenReturn(books3);
		Mockito.when(bookDao.search(null, null, AUTHOR, null)).thenReturn(books2);
		Mockito.when(bookDao.findAll()).thenReturn(books);
		Mockito.when(bookDao.findOne(1)).thenReturn(book);
		Mockito.when(bookDao.findOne(5)).thenReturn(null);
		Mockito.when(commonService.transform(books, BookDTO.class)).thenReturn(booksDto);
		Mockito.when(commonService.transform(book, BookDTO.class)).thenReturn(b2);
	}

	@Test
	public void testFindAll() {
		final List<BookDTO> booksDto = bookService.findAll(new PageRequest(0, 10));
		Assert.assertEquals("Error tamaño", 3, booksDto.size());
		Assert.assertEquals("Error autor", AUTHOR, booksDto.get(1).getAuthor());
		Assert.assertEquals("Error descripción", booksDto.get(1).getDescription(), DESCRIPTION);
		Assert.assertEquals("Error id", booksDto.get(1).getId(), ID);
		Assert.assertEquals("Error imagen", booksDto.get(1).getImage(), IMAGE);
		Assert.assertEquals("Error isbn", booksDto.get(1).getIsbn(), ISBN);
		Assert.assertEquals("Error estado", booksDto.get(1).getStatus(), BookEnum.OK.toString());
		Assert.assertEquals("Error título", booksDto.get(1).getTitle(), TITLE);
		Assert.assertEquals("Error año", booksDto.get(1).getYear(), YEAR);
	}
	
	@Test
	public void testFindOne() throws BookNotFoundException{
		final Book b = bookService.findOne(1);
		Assert.assertEquals("Error libro", book, b);
		Assert.assertNotNull(b);
		Assert.assertEquals("Error autor", AUTHOR, b.getAuthor());
		Assert.assertEquals("Error descripción", b.getDescription(), DESCRIPTION);
		Assert.assertEquals("Error id", b.getId(), ID);
		Assert.assertEquals("Error imagen", b.getImage(), IMAGE);
		Assert.assertEquals("Error isbn", b.getIsbn(), ISBN);
		Assert.assertEquals("Error estado", b.getStatus(), BookEnum.OK);
		Assert.assertEquals("Error título", b.getTitle(), TITLE);
		Assert.assertEquals("Error año", b.getYear(), YEAR);
	}
	
	@Test(expected = BookNotFoundException.class)
	public void testErrorFindOne() throws BookNotFoundException {
		bookService.findOne(-1);
	}
	
	@Test
	public void testSearch(){
		final List<BookDTO> booksExpected = new ArrayList<>();
		booksExpected.add(b2);
		final List<BookDTO> booksDto = bookService.search(null, TITLE, null, null);
		Assert.assertEquals("Error tamaño", 1, booksDto.size());
		Assert.assertEquals(booksExpected.get(0), book);
	}

}
