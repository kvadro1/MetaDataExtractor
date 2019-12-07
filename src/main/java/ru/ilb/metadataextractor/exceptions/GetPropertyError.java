package ru.ilb.metadataextractor.exceptions;

@SuppressWarnings("serial")
public class GetPropertyError extends NullPointerException {
	public GetPropertyError(String message) {
		super(message);
	}

}
