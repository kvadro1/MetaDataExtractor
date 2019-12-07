package ru.ilb.metadataextractor.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import ru.ilb.metadataextractor.constants.TextsConstantsForTests;
import ru.ilb.metadataextractor.core.MetadataExtractorFactory;
import ru.ilb.metadataextractor.entities.MetadataExtractor;

public class MetadataExtractorFactoryTest {
	@Test
	public void testGetPropertyIMG() throws Exception {
		System.out.println("JPEG extractMetadata");
		byte[] rawImage = Files.readAllBytes(
				Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.IMG_VALID).toURI()));
		MetadataExtractor imageExtractor = MetadataExtractorFactory.getMetadataExtractor(rawImage, "image/jpeg");
		String resultBarcode = imageExtractor.getProperty(TextsConstantsForTests.BARCODE);
		String resultSignatures = imageExtractor.getProperty(TextsConstantsForTests.SIGNATURES);
		Assert.assertEquals(TextsConstantsForTests.BARCODE_VALUE, resultBarcode);
		Assert.assertEquals(TextsConstantsForTests.SIGNATURES_VALUE, resultSignatures);
		System.out.println("JPEG extractMetadata end");
	}

	@Test
	public void testGetPropertyMapIMG() throws Exception {
		System.out.println("JPEG extractMetadata Map");
		byte[] rawImage = Files.readAllBytes(
				Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.IMG_VALID).toURI()));
		MetadataExtractor imageExtractor = MetadataExtractorFactory.getMetadataExtractor(rawImage, "image/jpeg");
		Map<String, String> propMap = imageExtractor.asMap();
		System.out.println(propMap.toString());
		Assert.assertEquals(propMap.size(), 2);
		System.out.println("JPEG extractMetadata Map end");
	}

	@Test
	public void testGetPropertyMapPDF() throws Exception {
		System.out.println("PDF extractMetadata Map");
		byte[] rawImage = Files.readAllBytes(
				Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.PDF_VALID).toURI()));
		MetadataExtractor pdfExtractor = MetadataExtractorFactory.getMetadataExtractor(rawImage, "application/pdf");
		Map<String, String> propMap = pdfExtractor.asMap();
		System.out.println(propMap.toString());
		Assert.assertEquals(propMap.size(), 2);
		System.out.println("PDF extractMetadata Map end");
	}

	@Test
	public void testGetPropertyPDF() throws Exception {
		System.out.println("PDF extractMetadata");
		byte[] rawPDF = Files.readAllBytes(
				Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.PDF_VALID).toURI()));
		MetadataExtractor pdfExtractor = MetadataExtractorFactory.getMetadataExtractor(rawPDF, "application/pdf");
		String resultBarcode = pdfExtractor.getProperty(TextsConstantsForTests.BARCODE);
		String resultSignatures = pdfExtractor.getProperty(TextsConstantsForTests.SIGNATURES);
		Assert.assertEquals(TextsConstantsForTests.BARCODE_VALUE, resultBarcode);
		Assert.assertEquals(TextsConstantsForTests.SIGNATURES_VALUE, resultSignatures);
		System.out.println("PDF extractMetadata end");
	}

	@Test
	public void testWrongMimeType() throws Exception{
		try {
			System.out.println("Wrong Mime Type Start");
			byte[] rawPDF = Files.readAllBytes(
					Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.PDF_VALID).toURI()));
			MetadataExtractor pdfExtractor = MetadataExtractorFactory.getMetadataExtractor(rawPDF, "");
		} catch (RuntimeException thrown) {
			Assert.assertEquals("Not supported mimetype", thrown.getMessage());
			System.out.println("Wrong Mime Type End");
		}
	}
}
