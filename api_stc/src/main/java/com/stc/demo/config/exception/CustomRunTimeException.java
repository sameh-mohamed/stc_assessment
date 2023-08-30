package com.stc.demo.config.exception;

public class CustomRunTimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String code;

	public CustomRunTimeException(String code, String message) {
		super(message);
		this.setCode(code);
	}

	public CustomRunTimeException() {
		super();
	}

	public CustomRunTimeException(String code, String message, Throwable cause) {
		super(message, cause);
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
