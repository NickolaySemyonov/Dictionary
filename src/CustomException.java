
public class CustomException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private int exceptionCode = 0; // default code
	
	public CustomException(int code, String message) 
	{
		super(message);
		exceptionCode = code;
	}
	
	public int GetCode() {
		return exceptionCode;
	}
}
