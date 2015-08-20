package com.skt.web.alpha.constants;

public final class Photo {

	private Photo() {
		throw new RuntimeException(
				"Cannot instantiate object of PhotoController class");
	}
	
	public static final String FILE_EMPTY = "You failed to upload because the file was empty.";
	public static final String ID_NULL = "ID Should not be null";
	
}
