package com.at.library.service.room;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.RoomDAO;
import com.at.library.dto.RoomDTO;
import com.at.library.dto.RoomPostDTO;
import com.at.library.exceptions.RoomAlreadyExistsException;
import com.at.library.exceptions.RoomNotFoundException;
import com.at.library.model.Room;
import com.at.library.service.CommonService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDAO roomDao;

	@Autowired
	private CommonService commonService;

	@Override
	@Transactional(readOnly = true)
	public List<RoomDTO> findAll(Pageable pageable) {
		final Iterator<Room> iterator = roomDao.findAll(pageable).iterator();
		final List<RoomDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Room r = iterator.next();
			res.add(commonService.transform(r, RoomDTO.class));
		}
		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public RoomDTO findOne(String code) throws RoomNotFoundException {
		final Room room = roomDao.findOne(code);
		if(room == null)
			throw new RoomNotFoundException();
		return commonService.transform(room, RoomDTO.class);
	}

	@Override
	public RoomDTO create(RoomPostDTO roomDto) throws RoomAlreadyExistsException {
		if(roomDao.findOne(roomDto.getCode()) != null)
			throw new RoomAlreadyExistsException();
		final Room room = commonService.transform(roomDto, Room.class);
		return commonService.transform(roomDao.save(room), RoomDTO.class);
	}

}
