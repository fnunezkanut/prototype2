package ie.nuigalway.sd3.error;

public class ErrorResponseException extends Exception {


	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}
	public ErrorResponseException( String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public ErrorResponseException() {
		super();
	}
}
