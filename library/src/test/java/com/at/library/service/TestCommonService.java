package com.at.library.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.at.library.dto.BookDTO;
import com.at.library.enums.BookEnum;
import com.at.library.model.Book;

@RunWith(MockitoJUnitRunner.class)
public class TestCommonService {
	
	@InjectMocks
	private CommonService commonService = new CommonServiceImpl();
	
	@Mock
	private DozerBeanMapper dozer;
	
	private Book book = new Book();
	private Book book2 = new Book();
	final List<Book> books = new ArrayList<>();
	private static final String AUTHOR = "Test Autor";
	private static final String DESCRIPTION = "Test Descripción";
	private static final Integer ID = 1;
	private static final String IMAGE = "Test Imagen";
	private static final String ISBN = "1111";
	private static final String TITLE = "Test Título";
	private static final Integer YEAR = 2000;
	
	@Before
	public void init(){
		books.add(book);
		books.add(book2);
		
		final BookDTO b1 = new BookDTO();
		b1.setAuthor(AUTHOR);
		b1.setDescription(DESCRIPTION);
		b1.setId(ID);
		b1.setImage(IMAGE);
		b1.setIsbn(ISBN);
		b1.setStatus(BookEnum.OK.toString());
		b1.setTitle(TITLE);
		b1.setYear(YEAR);
		
		final BookDTO b2 = new BookDTO();
		b2.setAuthor(AUTHOR.concat(" 2"));
		b2.setDescription(DESCRIPTION.concat(" 2"));
		b2.setId(ID+1);
		b2.setImage(IMAGE.concat(" 2"));
		b2.setIsbn(ISBN.concat(" 2"));
		b2.setStatus(BookEnum.RENTED.toString());
		b2.setTitle(TITLE.concat(" 2"));
		b2.setYear(YEAR+1);
		
		Mockito.when(dozer.map(book, BookDTO.class)).thenReturn(b1);
		Mockito.when(dozer.map(book2, BookDTO.class)).thenReturn(b2);
	}
	
	@Test
	public void testTransform(){
		final BookDTO bookDto = commonService.transform(book, BookDTO.class);
		Assert.assertEquals("Error autor", bookDto.getAuthor(), AUTHOR);
		Assert.assertEquals("Error descripción", bookDto.getDescription(), DESCRIPTION);
		Assert.assertEquals("Error id", bookDto.getId(), ID);
		Assert.assertEquals("Error imagen", bookDto.getImage(), IMAGE);
		Assert.assertEquals("Error isbn", bookDto.getIsbn(), ISBN);
		Assert.assertEquals("Error estado", bookDto.getStatus(), BookEnum.OK.toString());
		Assert.assertEquals("Error título", bookDto.getTitle(), TITLE);
		Assert.assertEquals("Error año", bookDto.getYear(), YEAR);
	}
	
	@Test
	public void testTransformList(){
		final List<BookDTO> booksDto = commonService.transform(books, BookDTO.class);
		Assert.assertEquals("Error tamaño", 2, booksDto.size());
		
		Assert.assertEquals("Error autor", booksDto.get(0).getAuthor(), AUTHOR);
		Assert.assertEquals("Error descripción", booksDto.get(0).getDescription(), DESCRIPTION);
		Assert.assertEquals("Error id", booksDto.get(0).getId(), ID);
		Assert.assertEquals("Error imagen", booksDto.get(0).getImage(), IMAGE);
		Assert.assertEquals("Error isbn", booksDto.get(0).getIsbn(), ISBN);
		Assert.assertEquals("Error estado", booksDto.get(0).getStatus(), BookEnum.OK.toString());
		Assert.assertEquals("Error título", booksDto.get(0).getTitle(), TITLE);
		Assert.assertEquals("Error año", booksDto.get(0).getYear(), YEAR);

		Assert.assertEquals("Error autor", booksDto.get(1).getAuthor(), AUTHOR.concat(" 2"));
		Assert.assertEquals("Error descripción", booksDto.get(1).getDescription(), DESCRIPTION.concat(" 2"));
		Assert.assertEquals("Error id", booksDto.get(1).getId(), Integer.valueOf(ID+1));
		Assert.assertEquals("Error imagen", booksDto.get(1).getImage(), IMAGE.concat(" 2"));
		Assert.assertEquals("Error isbn", booksDto.get(1).getIsbn(), ISBN.concat(" 2"));
		Assert.assertEquals("Error estado", booksDto.get(1).getStatus(), BookEnum.RENTED.toString());
		Assert.assertEquals("Error título", booksDto.get(1).getTitle(), TITLE.concat(" 2"));
		Assert.assertEquals("Error año", booksDto.get(1).getYear(), Integer.valueOf(YEAR+1));
		
	}

}
