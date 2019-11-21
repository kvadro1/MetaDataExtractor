package except;

@SuppressWarnings("serial")
public class CreateObjectError extends Exception {
	public CreateObjectError(String message) {
		super(message);
	}
}
