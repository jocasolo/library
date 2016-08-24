package com.at.library.service.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.RoomDAO;
import com.at.library.dto.RoomDTO;
import com.at.library.model.Room;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDAO roomDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public List<RoomDTO> findAll() {
		final Iterator<Room> iterator = roomDao.findAll().iterator();
		final List<RoomDTO> res = new ArrayList<>();
		
		while (iterator.hasNext()) {
			final Room r = iterator.next();
			res.add(transform(r));
		}
		return res;
	}

	@Override
	public RoomDTO transform(Room room) {
		return dozer.map(room, RoomDTO.class);
	}

	@Override
	public Room transform(RoomDTO room) {
		return dozer.map(room, Room.class);
	}

}
