package ru.ilb.metadataextractor.core;

import ru.ilb.metadataextractor.entities.MetadataExtractor;
import ru.ilb.metadataextractor.exceptions.MimeError;

public class MetadataExtractorFactory {
	public static MetadataExtractor getMetadataExtractor(byte[] document, String mimeType) {
		if (document == null || document.length <= 0) {
			return null;
		}
		MetadataExtractor result = null;
		switch (mimeType) {
		case "application/pdf":
			result = new PDFMetadataExtractor(document);
			return result;
		case "image/jpeg":
			result = new JPEGMetadataExtractor(document);
			return result;
		default:
			throw new MimeError("Not supported mimetype");
		}
	}

}
