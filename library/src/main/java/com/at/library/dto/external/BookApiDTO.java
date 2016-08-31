package com.at.library.dto.external;

import java.util.Arrays;

import com.at.library.dto.DTO;

public class BookApiDTO extends DTO {

	private static final long serialVersionUID = 2090071140970993542L;

	private ItemsDTO[] items;

	public ItemsDTO[] getItems() {
		return items;
	}

	public void setItems(ItemsDTO[] items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "BookApiDTO [items=" + Arrays.toString(items) + "]";
	}

}
