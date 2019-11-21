
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import constants.TextsConstantsForTests;
import entities.MetadataExtractorFactory;
import interfaces.MetadataExtractor;

public class MetadataExtractorFactoryTest {
	@Test
	public void testGetPropertyIMG() throws Exception {
		System.out.println("JPEG extractMetadata");
		byte[] rawImage = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.IMG_VALID).toURI()));
		MetadataExtractor imageExtractor = MetadataExtractorFactory.getMetadataExtractor(rawImage);
		String resultBarcode = imageExtractor.getProperty(TextsConstantsForTests.BARCODE);
		String resultSignatures = imageExtractor.getProperty(TextsConstantsForTests.SIGNATURES);
		Assert.assertEquals(TextsConstantsForTests.BARCODE_VALUE, resultBarcode);
		Assert.assertEquals(TextsConstantsForTests.SIGNATURES_VALUE, resultSignatures);
		System.out.println("JPEG extractMetadata end");
	}

	@Test
	public void testGetPropertyMapIMG() throws Exception {
		System.out.println("JPEG extractMetadata Map");
		byte[] rawImage = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.IMG_VALID).toURI()));
		MetadataExtractor imageExtractor = MetadataExtractorFactory.getMetadataExtractor(rawImage);
		Map<String, String> propMap = imageExtractor.asMap();
		System.out.println(propMap.toString());
		Assert.assertEquals(propMap.size(), 2);
		System.out.println("JPEG extractMetadata Map end");
	}

	@Test
	public void testGetPropertyMapPDF() throws Exception {
		System.out.println("PDF extractMetadata Map");
		byte[] rawImage = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.PDF_VALID).toURI()));
		MetadataExtractor pdfExtractor = MetadataExtractorFactory.getMetadataExtractor(rawImage);
		Map<String, String> propMap = pdfExtractor.asMap();
		System.out.println(propMap.toString());
		Assert.assertEquals(propMap.size(), 2);
		System.out.println("PDF extractMetadata Map end");
	}

	@Test
	public void testGetPropertyPDF() throws Exception {
		System.out.println("PDF extractMetadata");
		byte[] rawPDF = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.PDF_VALID).toURI()));
		MetadataExtractor pdfExtractor = MetadataExtractorFactory.getMetadataExtractor(rawPDF);
		String resultBarcode = pdfExtractor.getProperty(TextsConstantsForTests.BARCODE);
		String resultSignatures = pdfExtractor.getProperty(TextsConstantsForTests.SIGNATURES);
		Assert.assertEquals(TextsConstantsForTests.BARCODE_VALUE, resultBarcode);
		Assert.assertEquals(TextsConstantsForTests.SIGNATURES_VALUE, resultSignatures);
		System.out.println("PDF extractMetadata end");
	}
}
