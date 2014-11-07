package chemistry;

public class IllegalElementException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalElementException(String message){
		super(message);
	}

	public IllegalElementException() {
		super();
	}
}
