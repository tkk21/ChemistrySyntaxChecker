package chemistry;


public class ChemistrySyntaxChecker {

	private boolean hasParenthesis;
	private int parenthesisCount;
	private boolean justClosedParenthesis;
	
	private boolean hasLetter;
	private int letterCount;
	private boolean hasNumber;
	private static final String NOT_CHEMICALLY_VALID = "[^a-zA-Z0-9(-)]";
	
	public ChemistrySyntaxChecker () {
		hasParenthesis = false;
		hasLetter = false;
		hasNumber = false;
		
		parenthesisCount = 0;
		letterCount = 0;
		
		justClosedParenthesis = false;
	}
	
	public void checkSyntax(String s) {
		System.out.println(sanitize(s));
		s = sanitize(s);
		
		for (int i = 0; i<s.length(); i++){
			checkChar(s.charAt(i));
		}
	}
	
	private void checkChar (char c){
		switch(c){
		//parenthesis case
		case '(':
			parenthesisCount++;
			break;
		case ')':
			if (parenthesisCount<1){
				try {
					throw new IllegalParenthesisException();
				} catch (IllegalParenthesisException e) {
					ExceptionUtils.failChemistry();
				}
			}
			else{
				parenthesisCount--;
				justClosedParenthesis = true;
			}
			break;
			
		default:
			checkCompoundValidity(c);
			//call something else
		}
	}
	
	private void checkCompoundValidity(char c){
		
		
	}
	private String sanitize(String s) {
		String sanitized = s;
		sanitized = sanitized.replaceAll(NOT_CHEMICALLY_VALID, "");
		return sanitized;
	}
	
}


