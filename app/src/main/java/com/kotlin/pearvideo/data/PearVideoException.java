package com.kotlin.pearvideo.data;

public class PearVideoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1755802476069592558L;

	private int statusCode = 0 ; 
	
	public int getStatusCode() {
		return statusCode;
	}

	public PearVideoException() {
		super();
	}

	public PearVideoException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public PearVideoException(String detailMessage) {
		super(detailMessage);
	}

	public PearVideoException(Throwable throwable) {
		super(throwable);
	}
	
	public PearVideoException(int status) {
		super();
		this.statusCode = status;
	}

}
