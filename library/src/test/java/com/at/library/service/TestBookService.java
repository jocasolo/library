package com.at.library.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.at.library.dto.BookPostDTO;
import com.at.library.dto.external.BookApiDTO;
import com.at.library.dto.external.ItemsDTO;
import com.at.library.dto.external.VolumeInfoDTO;
import com.at.library.enums.BookEnum;
import com.at.library.exceptions.BookInvalidStatusException;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookWrongUpdateException;
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

	private static final String AUTHOR = "Test Autor";
	private static final String DESCRIPTION = "Test Descripción";
	private static final Integer ID = 0;
	private static final String IMAGE = "Test Imagen";
	private static final String ISBN = "1111";
	private static final String TITLE = "Test Título";
	private static final Integer YEAR = 2000;
	private static final Date INIT = new Date();

	private Book b1 = new Book();
	private Book b2 = new Book();
	private Book b3 = new Book();
	private Book b4 = new Book();
	private BookDTO bdto1 = new BookDTO();
	private BookDTO bdto2 = new BookDTO();
	private BookDTO bdto3 = new BookDTO();
	private BookDTO bdto4 = new BookDTO();
	private List<Book> books1 = new ArrayList<>();
	private List<Book> books2 = new ArrayList<>();
	private List<BookDTO> bdtos1 = new ArrayList<>();
	private List<BookDTO> bdtos2 = new ArrayList<>();
	private BookPostDTO bpost = new BookPostDTO();

	@Before
	public void init() {
		b1 = initBook(1);
		b2 = initBook(2);
		b3 = initBook(3);
		b4 = initBook(4);
		bdto1 = initBookDto(1);
		bdto2 = initBookDto(2);
		bdto3 = initBookDto(3);
		bdto4 = initBookDto(4);
		books1.add(b1);
		books1.add(b2);
		books2.add(b3);
		books2.add(b4);
		bdtos1.add(bdto1);
		bdtos1.add(bdto2);
		bdtos2.add(bdto3);
		bdtos2.add(bdto4);
		bpost.setAuthor(AUTHOR.concat(" 1"));
		bpost.setIsbn(ISBN.concat(" 1"));
		bpost.setTitle(TITLE.concat(" 1"));
		
		final String url = "https://www.googleapis.com/books/v1/volumes?startIndex=0&maxResults=1"
				+ "&fields=items(volumeInfo/description,volumeInfo/publishedDate,volumeInfo/imageLinks/thumbnail)&q=";
		final BookApiDTO info = new BookApiDTO();
		final ItemsDTO[] items = new ItemsDTO[1];
		items[0] = new ItemsDTO();
		final VolumeInfoDTO volInfo = new VolumeInfoDTO();
		volInfo.setDescription(DESCRIPTION.concat(" Info"));
		volInfo.setPublishedDate("2000");
		volInfo.getImageLinks().put("thumbnail", IMAGE.concat(" Info"));
		items[0].setVolumeInfo(volInfo);
		info.setItems(items);

		Mockito.when(bookDao.search(null, TITLE, null, null)).thenReturn(books1);
		Mockito.when(bookDao.search(null, null, AUTHOR, null)).thenReturn(books2);
		Mockito.when(bookDao.findAll(new PageRequest(0, 10))).thenReturn(books1);
		Mockito.when(bookDao.findOne(1)).thenReturn(b1);
		Mockito.when(bookDao.findOne(4)).thenReturn(b4);
		Mockito.when(bookDao.findOne(5)).thenReturn(null);
		Mockito.when(bookDao.save(b1)).thenReturn(b1);
		Mockito.when(bookDao.save(b4)).thenReturn(b4);
		Mockito.when(commonService.transform(books1, BookDTO.class)).thenReturn(bdtos1);
		Mockito.when(commonService.transform(books2, BookDTO.class)).thenReturn(bdtos2);
		Mockito.when(commonService.transform(b1, BookDTO.class)).thenReturn(bdto1);
		Mockito.when(commonService.transform(bpost, Book.class)).thenReturn(b1);
		Mockito.when(commonService.transform(bdto1, Book.class)).thenReturn(b1);
		Mockito.when(restTemplate.getForObject(url+b1.getTitle(), BookApiDTO.class)).thenReturn(info);
		Mockito.when(restTemplate.getForObject(url+b2.getTitle(), BookApiDTO.class)).thenReturn(null);
	}

	@Test
	public void testFindAll() {
		final List<BookDTO> booksDto = bookService.findAll(new PageRequest(0, 10));
		Assert.assertEquals("Error tamaño", 2, booksDto.size());
		Assert.assertEquals("Error autor", AUTHOR.concat(" 2"), booksDto.get(1).getAuthor());
		Assert.assertEquals("Error descripción", DESCRIPTION.concat(" 2"), booksDto.get(1).getDescription());
		Assert.assertEquals("Error id", Integer.valueOf(2), booksDto.get(1).getId());
		Assert.assertEquals("Error imagen", IMAGE.concat(" 2"), booksDto.get(1).getImage());
		Assert.assertEquals("Error isbn", ISBN.concat(" 2"), booksDto.get(1).getIsbn());
		Assert.assertEquals("Error estado", BookEnum.OK.toString(), booksDto.get(1).getStatus());
		Assert.assertEquals("Error título", TITLE.concat(" 2"), booksDto.get(1).getTitle());
		Assert.assertEquals("Error año", Integer.valueOf(2002), booksDto.get(1).getYear());
	}

	@Test
	public void testFindOne() throws BookNotFoundException {
		final Book b = bookService.findOne(1);
		Assert.assertEquals("Error libro", b1, b);
		Assert.assertNotNull(b);
		Assert.assertEquals("Error autor", AUTHOR.concat(" 1"), b.getAuthor());
		Assert.assertEquals("Error descripción", DESCRIPTION.concat(" 1"), b.getDescription());
		Assert.assertEquals("Error id", Integer.valueOf(1), b.getId());
		Assert.assertEquals("Error imagen", IMAGE.concat(" 1"), b.getImage());
		Assert.assertEquals("Error isbn", ISBN.concat(" 1"), b.getIsbn());
		Assert.assertEquals("Error estado", BookEnum.OK, b.getStatus());
		Assert.assertEquals("Error título", TITLE.concat(" 1"), b.getTitle());
		Assert.assertEquals("Error año", Integer.valueOf(2001), b.getYear());
	}

	@Test(expected = BookNotFoundException.class)
	public void testErrorFindOne() throws BookNotFoundException {
		bookService.findOne(-1);
	}

	@Test
	public void testSearch() {
		List<BookDTO> booksExpected = new ArrayList<>();
		booksExpected.add(bdto1);
		booksExpected.add(bdto2);
		List<BookDTO> booksDto = bookService.search(null, TITLE, null, null);
		Assert.assertEquals("Error tamaño", 2, booksDto.size());
		Assert.assertEquals("Error libro", booksExpected.get(0), bdto1);
		Assert.assertEquals("Eror título", booksExpected.get(1).getTitle(), TITLE.concat(" 2"));

		booksExpected.clear();
		booksExpected.add(bdto3);
		booksExpected.add(bdto4);
		booksDto = bookService.search(null, null, AUTHOR, null);
		Assert.assertEquals("Error tamaño", 2, booksDto.size());
		Assert.assertEquals("Error libro", booksExpected.get(0), bdto3);
		Assert.assertEquals("Error título", booksExpected.get(1).getAuthor(), AUTHOR.concat(" 4"));
	}

	@Test
	public void testCreate() {
		final BookDTO bdto = bookService.create(bpost);
		Assert.assertNotNull(bdto);
		Assert.assertEquals(TITLE.concat(" 1"), bdto.getTitle());
		Assert.assertEquals(AUTHOR.concat(" 1"), bdto.getAuthor());
		Assert.assertEquals(ISBN.concat(" 1"), bdto.getIsbn());
		Assert.assertEquals(BookEnum.OK.toString(), bdto.getStatus());
	}

	@Test
	public void testUpdate() throws BookWrongUpdateException, BookInvalidStatusException {
		bookService.update(1, bdto1);
		Assert.assertNotNull(bdto1);
		Assert.assertEquals(TITLE.concat(" 1"), bdto1.getTitle());
		Assert.assertEquals(AUTHOR.concat(" 1"), bdto1.getAuthor());
		Assert.assertEquals(ISBN.concat(" 1"), bdto1.getIsbn());
		Assert.assertEquals(BookEnum.OK.toString(), bdto1.getStatus());
	}
	
	@Test(expected = BookWrongUpdateException.class)
	public void testErrorUpdate1() throws BookWrongUpdateException, BookInvalidStatusException {
		bookService.update(2, bdto1);
	}
	
	@Test(expected = BookInvalidStatusException.class)
	public void testErrorUpdate2() throws BookWrongUpdateException, BookInvalidStatusException {
		final BookDTO b = new BookDTO();
		b.setId(null);
		bookService.update(2, b);
	}
	
	@Test
	public void testDelete() throws BookNotFoundException{
		bookService.delete(4);
		Assert.assertEquals(BookEnum.DELETED, b4.getStatus());
	}
	
	@Test
	public void testIsAvailable(){
		Assert.assertTrue(bookService.isAvailable(b1));
		b4.setStatus(BookEnum.DELETED);
		Assert.assertFalse(bookService.isAvailable(b4));
		b4.setStatus(BookEnum.RENTED);
		Assert.assertFalse(bookService.isAvailable(b4));
		b4.setStatus(null);
		Assert.assertFalse(bookService.isAvailable(b4));
		b4.setStatus(BookEnum.OK);
	}
	
	@Test
	public void testChangeStatus(){
		bookService.changeStatus(b4, BookEnum.DELETED);
		Assert.assertEquals(BookEnum.DELETED, b4.getStatus());
		bookService.changeStatus(b4, BookEnum.RENTED);
		Assert.assertEquals(BookEnum.RENTED, b4.getStatus());
		bookService.changeStatus(b4, BookEnum.OK);
		Assert.assertEquals(BookEnum.OK, b4.getStatus());
		bookService.changeStatus(b4, BookEnum.OK);
		Assert.assertEquals(BookEnum.OK, b4.getStatus());
	}
	
	@Test
	public void setVolumeInfo(){
		final Book b = new Book();
		final Book b2 = new Book();
		b.setTitle(TITLE.concat(" 1"));
		bookService.setVolumeInfo(b);
		Assert.assertEquals(DESCRIPTION.concat(" Info"), b.getDescription());
		Assert.assertEquals(IMAGE.concat(" Info"), b.getImage());
		Assert.assertEquals(Integer.valueOf(2000), b.getYear());
		bookService.setVolumeInfo(b2);
		Assert.assertNull(b2.getDescription());
		Assert.assertNull(b2.getImage());
		Assert.assertNull(b2.getYear());
	}

	private Book initBook(Integer index) {
		final Book b = new Book();
		b.setAuthor(AUTHOR.concat(" " + index));
		b.setDescription(DESCRIPTION.concat(" " + index));
		b.setId(ID + index);
		b.setImage(IMAGE.concat(" " + index));
		b.setIsbn(ISBN.concat(" " + index));
		b.setStartDate(INIT);
		b.setStatus(BookEnum.OK);
		b.setTitle(TITLE.concat(" " + index));
		b.setYear(YEAR + index);
		return b;
	}

	private BookDTO initBookDto(Integer index) {
		final BookDTO b = new BookDTO();
		b.setAuthor(AUTHOR.concat(" " + index));
		b.setDescription(DESCRIPTION.concat(" " + index));
		b.setId(ID + index);
		b.setImage(IMAGE.concat(" " + index));
		b.setIsbn(ISBN.concat(" " + index));
		b.setStatus(BookEnum.OK.toString());
		b.setTitle(TITLE.concat(" " + index));
		b.setYear(YEAR + index);
		return b;
	}

}
