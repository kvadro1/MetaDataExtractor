package ru.ilb.metadataextractor.test;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import ru.ilb.metadataextractor.constants.TextsConstantsForTests;
import ru.ilb.metadataextractor.core.JPEGMetadataExtractor;

public class JPEGMetadataExtractorTest {

	public JPEGMetadataExtractorTest() {
	}

	@Test
	public void testGetProperty() throws Exception {
		System.out.println("JPEG extractMetadata");
		byte[] rawImage = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.IMG_VALID).toURI()));
		JPEGMetadataExtractor imageExtractor = new JPEGMetadataExtractor(rawImage);
		String resultBarcode = imageExtractor.getProperty(TextsConstantsForTests.BARCODE);
		String resultSignatures = imageExtractor.getProperty(TextsConstantsForTests.SIGNATURES);
		Assert.assertEquals(TextsConstantsForTests.BARCODE_VALUE, resultBarcode);
		Assert.assertEquals(TextsConstantsForTests.SIGNATURES_VALUE, resultSignatures);
		System.out.println("JPEG extractMetadata end");
	}

}
