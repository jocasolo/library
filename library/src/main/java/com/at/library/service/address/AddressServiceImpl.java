package com.at.library.service.address;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.AddressDAO;
import com.at.library.dto.AddressDTO;
import com.at.library.model.Address;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDAO addressDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<AddressDTO> findAll() {
		final Iterable<Address> findAll = addressDao.findAll();
		final Iterator<Address> iterator = findAll.iterator();
		final List<AddressDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Address b = iterator.next();
			final AddressDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;
	}

	@Override
	public AddressDTO transform(Address address) {
		return dozer.map(address, AddressDTO.class);
	}

	@Override
	public Address transform(AddressDTO address) {
		return dozer.map(address, Address.class);
	}

}
