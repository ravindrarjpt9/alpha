package com.skt.web.common.exception;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 8240601137428783154L;

	private final int errorCode = 1001;

	public ApplicationException(String message) {
		super(message);
	}

	public int getErrorCode() {
		return errorCode;
	}
}
