package chemistry;


public class ChemistrySyntaxChecker {

	public enum ChemistryCharacterStatus{
		firstCharacter, openParenthesis, closedParenthesis, 
		upperLetter, lowerLetter, number;
	}
	
	private boolean firstCharacter;
	
	private int parenthesisCount;
	
	private boolean afterOpenParenthesis; 
	private boolean afterClosedParenthesis;
	
	private boolean afterLetter;
	private boolean afterStartElement;
	
	private ChemistryCharacterStatus beforeCharacter;
	private int letterCount;
	
	private boolean afterNumber;
	
	private static final String NOT_CHEMICALLY_VALID = "[^a-zA-Z0-9(-)]";
	

	public ChemistrySyntaxChecker () {
		
		beforeCharacter = ChemistryCharacterStatus.firstCharacter;
		
		firstCharacter = true;
		
		parenthesisCount = 0;
		letterCount = 0;
		
		afterClosedParenthesis = false;
		afterLetter = false;
		afterStartElement = false;
		
		afterNumber = false;
	}
	
	public void checkSyntax(String s) {
		System.out.println(sanitize(s));
		s = sanitize(s);
		
		for (int i = 0; i<s.length(); i++){
			checkChar(s.charAt(i));
		}
	}
	private void processChemistrySyntax(){
		switch(beforeCharacter){
		case firstCharacter:
			
		}
	}
	private void legalChars(){
		if (firstCharacter){
			firstCharacter = false;
			//anything allowed
		}
		if (afterStartElement){
			//capital letter CH
			//lower letter Co
			//number H2
			//parenthesis H(O)
		}
		if (afterLetter){
			//capital letter HeO
			//another letter Hea
			//number He2
			//parenthesis He(O)
		}
		if (afterNumber){
			//capital letter H2O
			//number H23
			//parenthesis H2(O)
		}
		if (afterOpenParenthesis){
			//capital letter (H)
			//number (3Co)
			//parenthesis ((He))
		}
		if (afterClosedParenthesis){
			//capital letter (He)O
			//number (He)3
			//parenthesis (He)(Ho)
		}
	}
	
	private void startElement(){
		
	}
	private void checkChar (char c){
		if (afterStartElement){
			
		}
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
				afterClosedParenthesis = true;
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


