package com.at.library.service.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.at.library.dao.BookDAO;
import com.at.library.dto.BookDTO;
import com.at.library.dto.BookPostDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.external.BookApiDTO;
import com.at.library.dto.external.VolumeInfoDTO;
import com.at.library.enums.BookEnum;
import com.at.library.exceptions.BookInvalidStatusException;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookWrongUpdateException;
import com.at.library.model.Book;
import com.at.library.service.CommonService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDao;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CommonService commonService;

	@Override
	@Transactional(readOnly = true)
	public List<BookDTO> findAll(Pageable pageable) {
		final List<Book> books = bookDao.findAll(pageable);
		return commonService.transform(books, BookDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Book findOne(Integer id) throws BookNotFoundException {
		final Book book = bookDao.findOne(id);
		if (book == null)
			throw new BookNotFoundException();

		return book;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDTO> search(String isbn, String title, String author, Pageable pageable) {
		final List<Book> books = bookDao.search(isbn, title, author, pageable);
		return commonService.transform(books, BookDTO.class);
	}

	@Override
	public BookDTO create(BookPostDTO bookDto) {
		Book book = commonService.transform(bookDto, Book.class);
		book.setStartDate(new Date());
		book.setStatus(BookEnum.OK);
		setVolumeInfo(book);
		return commonService.transform(bookDao.save(book), BookDTO.class);
	}

	@Override
	public void update(Integer id, BookDTO bookDto) throws BookWrongUpdateException, BookInvalidStatusException {
		if (bookDto.getId() != null && id != bookDto.getId())
			throw new BookWrongUpdateException();
		if(!isValidStatus(bookDto.getStatus()))
			throw new BookInvalidStatusException();

		final Book book = commonService.transform(bookDto, Book.class);
		book.setId(id);
		setVolumeInfo(book);
		bookDao.save(book);
	}

	@Override
	public void delete(Integer id) throws BookNotFoundException {
		Book book = findOne(id);
		book.setStatus(BookEnum.DELETED);
		bookDao.save(book);
	}

	@Override
	public Boolean isAvailable(Book book) {
		return book.getStatus() != null && book.getStatus().equals(BookEnum.OK);
	}

	@Override
	public void changeStatus(Book book, BookEnum newStatus) {
		if (!book.getStatus().equals(newStatus)) {
			book.setStatus(newStatus);
			bookDao.save(book);
		}
	}

	@Override
	public void setVolumeInfo(Book book) {
		final String url = "https://www.googleapis.com/books/v1/volumes?startIndex=0&maxResults=1"
				+ "&fields=items(volumeInfo/description,volumeInfo/publishedDate,volumeInfo/imageLinks/thumbnail)&q="
				+ book.getTitle();
		final BookApiDTO info = restTemplate.getForObject(url, BookApiDTO.class);

		if (info != null) {
			final VolumeInfoDTO volInfo = info.getItems()[0].getVolumeInfo();
			book.setDescription(volInfo.getDescription());
			book.setYear(Integer.parseInt(volInfo.getPublishedDate().substring(0, 4)));
			book.setImage(volInfo.getImageLinks().get("thumbnail"));
		}
	}

	@Override
	public void migration() {
		final String url = "http://192.168.11.57:8080/rent";
		final Integer size = 20;
		Integer page = 0;
		final List<Integer> books = new ArrayList<>();

		RentDTO[] rents = restTemplate.getForObject(url + "?page=" + page + "&size=" + size, RentDTO[].class);
		while (rents != null) {
			final Iterator<RentDTO> iterator = Arrays.asList(rents).iterator();
			while (iterator.hasNext()) {
				Book book = commonService.transform(iterator.next().getBook(), Book.class);
				if (!books.contains(book.getId())) {
					books.add(book.getId());
					book.setId(iterator.next().getBook().getId());
					bookDao.save(book);
				}
			}
			page++;
			rents = restTemplate.getForObject(url + "?page=" + page + "&size=" + size, RentDTO[].class);
		}
	}

	@Override
	public boolean isValidStatus(String status) {
		try{
			BookEnum.valueOf(status);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

}
