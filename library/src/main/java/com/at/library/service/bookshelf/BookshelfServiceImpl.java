package com.at.library.service.bookshelf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.BookshelfDAO;
import com.at.library.dto.BookshelfDTO;
import com.at.library.exceptions.BookshelfNotFoundException;
import com.at.library.model.Bookshelf;
import com.at.library.service.CommonService;

@Service
public class BookshelfServiceImpl implements BookshelfService {

	@Autowired
	private BookshelfDAO shelfDao;

	@Autowired
	private CommonService commonService;

	@Override
	@Transactional(readOnly = true)
	public List<BookshelfDTO> findAll(Pageable pageable) {
		final Iterator<Bookshelf> iterator = shelfDao.findAll(pageable).iterator();
		final List<BookshelfDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Bookshelf r = iterator.next();
			res.add(commonService.transform(r, BookshelfDTO.class));
		}
		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public BookshelfDTO findOne(Integer id) throws BookshelfNotFoundException {
		final Bookshelf shelf = shelfDao.findOne(id);
		if (shelf == null)
			throw new BookshelfNotFoundException();
		return commonService.transform(shelf, BookshelfDTO.class);
	}

	@Override
	public BookshelfDTO create(BookshelfDTO shelfDto) {
		final Bookshelf shelf = commonService.transform(shelfDto, Bookshelf.class);
		return commonService.transform(shelfDao.save(shelf), BookshelfDTO.class);
	}

}
