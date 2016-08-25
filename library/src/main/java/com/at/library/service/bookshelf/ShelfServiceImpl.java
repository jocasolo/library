package com.at.library.service.bookshelf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.ShelfDAO;
import com.at.library.dto.BookshelfDTO;
import com.at.library.dto.BookshelfPutDTO;
import com.at.library.model.Bookshelf;

@Service
public class ShelfServiceImpl implements ShelfService {

	@Autowired
	private ShelfDAO shelfDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<BookshelfDTO> findAll() {
		final Iterator<Bookshelf> iterator = shelfDao.findAll().iterator();
		final List<BookshelfDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Bookshelf r = iterator.next();
			res.add(transform(r));
		}
		return res;
	}

	@Override
	public BookshelfDTO findOne(Integer id) {
		final Bookshelf shelf = shelfDao.findOne(id);
		return transform(shelf);
	}

	@Override
	public BookshelfDTO create(BookshelfDTO shelfDto) {
		final Bookshelf shelf = transform(shelfDto);
		return transform(shelfDao.save(shelf));
	}

	@Override
	public void update(BookshelfPutDTO shelfDto) {
		Bookshelf shelf = transform(shelfDto);
		shelfDao.save(shelf);
	}

	@Override
	public void delete(Integer id) {
		shelfDao.delete(id);
	}

	@Override
	public BookshelfDTO transform(Bookshelf shelf) {
		return dozer.map(shelf, BookshelfDTO.class);
	}

	@Override
	public <T> Bookshelf transform(T shelfDto) {
		return dozer.map(shelfDto, Bookshelf.class);
	}

}
