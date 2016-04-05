package com.tarena.common;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -3547597164537346433L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
