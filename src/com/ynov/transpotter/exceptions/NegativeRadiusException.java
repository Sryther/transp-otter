package com.ynov.transpotter.exceptions;

public class NegativeRadiusException extends Exception {
	private static final long serialVersionUID = -1441347840687528146L;
	private static String message = "The radius must not be negative.";

	public NegativeRadiusException() {
		super(message);
	}

	public NegativeRadiusException(String msg) {
		super(msg);
	}

	public NegativeRadiusException(Throwable cause) {
		super(cause);
	}

	public NegativeRadiusException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NegativeRadiusException(String msg, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(msg, cause, enableSuppression, writableStackTrace);
	}

}
