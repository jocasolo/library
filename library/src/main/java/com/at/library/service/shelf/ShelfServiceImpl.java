package com.at.library.service.shelf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.ShelfDAO;
import com.at.library.dto.ShelfDTO;
import com.at.library.dto.ShelfPutDTO;
import com.at.library.model.Shelf;

@Service
public class ShelfServiceImpl implements ShelfService {

	@Autowired
	private ShelfDAO shelfDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<ShelfDTO> findAll() {
		final Iterator<Shelf> iterator = shelfDao.findAll().iterator();
		final List<ShelfDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Shelf r = iterator.next();
			res.add(transform(r));
		}
		return res;
	}

	@Override
	public ShelfDTO findOne(Integer id) {
		final Shelf shelf = shelfDao.findOne(id);
		return transform(shelf);
	}

	@Override
	public ShelfDTO create(ShelfDTO shelfDto) {
		final Shelf shelf = transform(shelfDto);
		return transform(shelfDao.save(shelf));
	}

	@Override
	public void update(ShelfPutDTO shelfDto) {
		Shelf shelf = transform(shelfDto);
		shelfDao.save(shelf);
	}

	@Override
	public void delete(Integer id) {
		shelfDao.delete(id);
	}

	@Override
	public ShelfDTO transform(Shelf shelf) {
		return dozer.map(shelf, ShelfDTO.class);
	}

	@Override
	public <T> Shelf transform(T shelfDto) {
		return dozer.map(shelfDto, Shelf.class);
	}

}
