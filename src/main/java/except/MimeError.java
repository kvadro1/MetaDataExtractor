package except;

import java.io.IOException;

@SuppressWarnings("serial")
public class MimeError extends IOException {
	public MimeError(String message) {
		super(message);
	}
}
