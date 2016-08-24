package com.at.library.dto;

import java.io.Serializable;
import java.util.List;

public class RoomDTO implements Serializable {

	private static final long serialVersionUID = 8900147020824277792L;
	
	private String name;
	
	private String description;
	
	private List<ShelfDTO> shelves;

}
