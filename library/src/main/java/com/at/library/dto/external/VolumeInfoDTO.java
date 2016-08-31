package com.at.library.dto.external;

import java.util.HashMap;
import java.util.Map;

import com.at.library.dto.DTO;

public class VolumeInfoDTO extends DTO {

	private static final long serialVersionUID = 9069790615502060130L;

	private String description;

	private String publishedDate;

	private Map<String, String> imageLinks = new HashMap<String, String>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Map<String, String> getImageLinks() {
		return imageLinks;
	}

	public void setImageLinks(Map<String, String> imageLinks) {
		this.imageLinks = imageLinks;
	}

	@Override
	public String toString() {
		return "VolumenInfoDTO [description=" + description + ", publishedDate=" + publishedDate + ", imageLinks="
				+ imageLinks + "]";
	}
}
