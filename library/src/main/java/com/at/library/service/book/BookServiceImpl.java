package com.at.library.service.book;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.BookDAO;
import com.at.library.dto.BookDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.BookWrongUpdateException;
import com.at.library.model.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	@Transactional(readOnly = true)
	public List<BookDTO> findAll() {
		final Iterator<Book> iterator = bookDao.findAll().iterator();
		final List<BookDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			res.add(transform(b));
		}
		return res;
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
	public List<BookDTO> search(String isbn, String title, String author, StatusEnum status) {
		return bookDao.findByIsbnOrTitleOrAuthorOrStatusAllIgnoreCase(isbn, title, author, status);
	}

	@Override
	public BookDTO create(BookDTO bookDto) {
		Book book = transform(bookDto);
		book.setStartDate(new Date());
		return transform(bookDao.save(book));
	}

	@Override
	public void update(Integer id, BookDTO bookDto) throws BookWrongUpdateException {
		if(id != bookDto.getId())
			throw new BookWrongUpdateException();
			
		final Book book = transform(bookDto);
		bookDao.save(book);
	}

	@Override
	public void delete(Integer id) throws BookNotFoundException {
		Book book = bookDao.findOne(id);
		if(book == null)
			throw new BookNotFoundException();
		book.setStatus(StatusEnum.DELETED);
		bookDao.save(book);
	}

	@Override
	public Boolean isAvailable(Book book) {
		return book.getStatus().equals(StatusEnum.ACTIVE);
	}

	@Override
	public Boolean isAvailable(Integer id) throws BookNotFoundException {
		final Book b = bookDao.findOne(id);
		if(b == null)
			throw new BookNotFoundException();
		return b.getStatus().equals(StatusEnum.ACTIVE);
	}

	@Override
	public void changeStatus(Book book, StatusEnum newStatus) {
		if (!book.getStatus().equals(newStatus)) {
			book.setStatus(newStatus);
			bookDao.save(book);
		}
	}

	@Override
	public BookDTO transform(Book book) {
		return dozer.map(book, BookDTO.class);
	}

	@Override
	public <T> Book transform(T book) {
		return dozer.map(book, Book.class);
	}

	public List<BookDTO> transform(List<Book> books) {
		List<BookDTO> res = new ArrayList<>();
		for (Book b : books)
			res.add(transform(b));
		return res;
	}

}
