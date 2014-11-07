package chemistry;

public class IllegalParenthesisException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalParenthesisException(String message) {
		super(message);
	}
	
	public IllegalParenthesisException(){
		super();
	}
}
