package exceptions;

public class MyError {

	private int errorType;
	private String errorTitle;
	private String errorMessage;
	
	public MyError(int errorType, String errorTitle, String errorMessage) {
		this.errorType = errorType;
		this.errorTitle = errorTitle;
		this.errorMessage = errorMessage;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
