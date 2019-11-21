package entities;

import org.apache.tika.Tika;

import except.CreateObjectError;
import except.MimeError;
import interfaces.MetadataExtractor;

public class MetadataExtractorFactory {
	public static MetadataExtractor getMetadataExtractor(byte[] document) throws MimeError, CreateObjectError {
		if (document == null || document.length <= 0) {
			return null;
		}
		String mimeType = null;
		MetadataExtractor result = null;
		mimeType = new Tika().detect(document);
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

	public static MetadataExtractor getMetadataExtractor(byte[] document, String mimeType) throws MimeError, CreateObjectError {
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
