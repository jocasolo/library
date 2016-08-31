package com.at.library.dto.external;

import com.at.library.dto.DTO;

public class ItemsDTO extends DTO {

	private static final long serialVersionUID = -8028640061410682596L;
	
	private VolumeInfoDTO volumeInfo;

	@Override
	public String toString() {
		return "ItemsDTO [volumeInfo=" + volumeInfo + "]";
	}

	public VolumeInfoDTO getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(VolumeInfoDTO volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
}
