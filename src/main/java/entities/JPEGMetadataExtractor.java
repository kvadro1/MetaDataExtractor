package entities;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import com.adobe.internal.xmp.XMPException;
import com.adobe.internal.xmp.XMPIterator;
import com.adobe.internal.xmp.XMPMeta;
import com.adobe.internal.xmp.properties.XMPPropertyInfo;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.xmp.XmpDirectory;

import except.CreateObjectError;
import except.GetPropertyError;
import interfaces.MetadataExtractor;

public class JPEGMetadataExtractor implements MetadataExtractor {
	private String schema = "http://ns.adobe.com/xap/1.0/";// just simple/ in constructor we read name space
	private XMPMeta xmpMeta;

	public JPEGMetadataExtractor(byte[] document) throws CreateObjectError {
		try (BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(document));) {
			Metadata metadata = ImageMetadataReader.readMetadata(is);
			XmpDirectory directory = metadata.getFirstDirectoryOfType(XmpDirectory.class);
			xmpMeta = directory.getXMPMeta();
			XMPIterator itr = xmpMeta.iterator();
			// try get scheme of doc
			if (itr.hasNext()) {
				XMPPropertyInfo pi = (XMPPropertyInfo) itr.next();
				String temp = pi.getNamespace();
				if (temp != null)
					schema = temp;
			}
		} catch (Exception e) {
			throw new CreateObjectError("XMP JPEG parse error: " + e);
		}
	}

	@Override
	public String getProperty(String propName) throws GetPropertyError {
		String propValue = null;
		try {
			propValue = xmpMeta.getPropertyString(schema, propName);
			return propValue;
		} catch (XMPException e) {
			throw new GetPropertyError("XMP JPEG parse error: " + e);
		}
	}

	@Override
	public Map<String, String> asMap() throws GetPropertyError {
		Map<String, String> result = new HashMap<String, String>();
		try {
			XMPIterator iter = xmpMeta.iterator();
			while (iter.hasNext()) {
				XMPPropertyInfo propInfo = (XMPPropertyInfo) iter.next();
				if (propInfo.getValue() != null)
					result.put(propInfo.getPath(), propInfo.getValue());
			}
		} catch (Exception e) {
			throw new GetPropertyError("XMP JPEG MAP properties parse error: " + e);
		}
		return result;
	}

}
