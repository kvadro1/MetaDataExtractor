package ru.ilb.metadataextractor.exceptions;

@SuppressWarnings("serial")
public class MimeError extends IllegalArgumentException {
	public MimeError(String message) {
		super(message);
	}
}
