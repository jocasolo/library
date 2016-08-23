package com.at.library.service.shelf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.ShelfDAO;
import com.at.library.dto.ShelfDTO;
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
	public ShelfDTO transform(Shelf shelf) {
		return dozer.map(shelf, ShelfDTO.class);
	}

	@Override
	public Shelf transform(ShelfDTO shelf) {
		return dozer.map(shelf, Shelf.class);
	}

}
