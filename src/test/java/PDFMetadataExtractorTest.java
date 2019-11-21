import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import constants.TextsConstantsForTests;
import entities.PDFMetadataExtractor;

public class PDFMetadataExtractorTest {
	public PDFMetadataExtractorTest() {
	}

	@Test
	public void testWriteReadProperty() throws Exception {
		System.out.println("PDF extractMetadata");

		HashMap<String, String> valuesToRecord = new HashMap<String, String>();
		valuesToRecord.put(TextsConstantsForTests.BARCODE, TextsConstantsForTests.BARCODE_VALUE);
		valuesToRecord.put(TextsConstantsForTests.SIGNATURES, TextsConstantsForTests.SIGNATURES_VALUE);

		byte[] rawInImage = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(TextsConstantsForTests.PDF_VALID).toURI()));
		PDFMetadataExtractor forWrite = new PDFMetadataExtractor(rawInImage);
		byte[] writedPdf = forWrite.insertMetadata(valuesToRecord);
		URL urlLoader = getClass().getProtectionDomain().getCodeSource().getLocation();
		try (FileOutputStream fos = new FileOutputStream(new File(urlLoader.getPath() + "/ouput.pdf"))) {
			fos.write(writedPdf);
		}
		PDFMetadataExtractor pdfXMPExtractor = new PDFMetadataExtractor(writedPdf);
		String resultBarcode = pdfXMPExtractor.getProperty(TextsConstantsForTests.BARCODE);
		String resultSignatures = pdfXMPExtractor.getProperty(TextsConstantsForTests.SIGNATURES);
		Assert.assertEquals(TextsConstantsForTests.BARCODE_VALUE, resultBarcode);
		Assert.assertEquals(TextsConstantsForTests.SIGNATURES_VALUE, resultSignatures);
		System.out.println("PDF extractMetadata end");
	}

}
