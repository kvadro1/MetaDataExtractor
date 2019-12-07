package ru.ilb.metadataextractor.exceptions;

@SuppressWarnings("serial")
public class CreateObjectError extends NullPointerException {
	public CreateObjectError(String message) {
		super(message);
	}
}
