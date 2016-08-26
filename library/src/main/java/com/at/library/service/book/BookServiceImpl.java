package com.at.library.service.book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.BookDAO;
import com.at.library.dto.BookDTO;
import com.at.library.enums.StatusEnum;
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
	public Book findOne(Integer id) {
		final Book book = bookDao.findOne(id);
		return book;
	}

	@Override
	public BookDTO create(BookDTO bookDto) {
		final Book book = transform(bookDto);
		return transform(bookDao.save(book));
	}

	@Override
	public void update(BookDTO bookDto) {
		final Book book = transform(bookDto);
		bookDao.save(book);
	}

	@Override
	public void delete(Integer id) {
		bookDao.delete(id);
	}
	
	@Override
	public BookDTO transform(Book book) {
		return dozer.map(book, BookDTO.class);
	}

	@Override
	public Book transform(BookDTO book) {
		return dozer.map(book, Book.class);
	}

	@Override
	public Boolean isAvailable(Book book) {
		return book.getStatus().equals(StatusEnum.ACTIVE);
	}

	@Override
	public Boolean isAvailable(Integer id) {
		final Book b = bookDao.findOne(id);
		return b.getStatus().equals(StatusEnum.ACTIVE);
	}

	@Override
	public void changeStatus(Book book, StatusEnum newStatus) {
		if(!book.getStatus().equals(newStatus))
			book.setStatus(newStatus);
	}

}
