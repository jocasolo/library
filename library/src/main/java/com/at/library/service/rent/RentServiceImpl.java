package com.at.library.service.rent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RentDAO;
import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private RentDAO rentDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<RentDTO> findAll() {
		final Iterator<Rent> iterator = rentDao.findAll().iterator();
		final List<RentDTO> res = new ArrayList<>();
		
		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			res.add(transform(r));
		}
		return res;
	}

	@Override
	public RentDTO transform(Rent rent) {
		return dozer.map(rent, RentDTO.class);
	}

	@Override
	public Rent transform(RentDTO rent) {
		return dozer.map(rent, Rent.class);
	}

}
