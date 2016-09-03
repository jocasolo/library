package com.at.library.service;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.at.library.dao.RentDAO;
import com.at.library.dto.RentDTO;
import com.at.library.model.Rent;
import com.at.library.service.rent.RentService;
import com.at.library.service.rent.RentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestRentService {
	
	private static final Date INIT = new Date();
	
	private Rent rent = new Rent();
	
	@InjectMocks
	private RentService rentService = new RentServiceImpl();
	
	@Mock
	private DozerBeanMapper dozer;
	
	@Mock
	private RentDAO rentDao;
	
	@Before
	public void init(){
		final RentDTO r = new RentDTO();
		r.setInitDate(INIT);
		Mockito.when(dozer.map(rent, RentDTO.class)).thenReturn(r);
		Mockito.when(rentDao.findOne(1)).thenReturn(rent);
	}
	
	@Test
	@Ignore
	public void testCreate(){
		createRent();
	}
	
	@Test
	public void transformRent(){
		//final RentDTO rentDTO = rentService.transform(rent, RentDTO.class);
		//Assert.assertEquals("Fecha", rentDTO.getInitDate(), INIT);
	}
	
	private void createRent(){
		rent.setInitDate(INIT);
	}
	
}
