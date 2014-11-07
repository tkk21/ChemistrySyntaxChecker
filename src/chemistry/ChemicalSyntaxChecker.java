package chemistry;

/**
 * a class that checks the chemical syntax of an input string
 * 
 * @author ted
 *
 */
public class ChemicalSyntaxChecker {

	/**
	 * enum of the possible characters in a chemical molecule
	 * used to record what character was in the string
	 * @author ted
	 *
	 */
	public enum ChemicalCharacter{
		openParenthesis, closedParenthesis, 
		upperLetter, lowerLetter, number;

		/**
		 * takes in a character and returns what the character is in terms of chemical character
		 * @param c	the character to convert
		 * @return	the chemical character enum version
		 */
		public static ChemicalCharacter getCharacterClassification(char c){
			String s = ""+c;
			if (s.matches(UPPER_LETTER)){
				return ChemicalCharacter.upperLetter;
			}
			if (s.matches(LOWER_LETTER)){
				return ChemicalCharacter.lowerLetter;
			}
			if (s.matches(NUMBER)){
				return ChemicalCharacter.number;
			}
			else {
				return getParenthesisClassification(s);
			}

		}

		/**
		 * a helper method that converts parenthesis character into chemical character
		 * @param s	the string representing the character to convert
		 * @return	the chemical character enum version
		 */
		private static ChemicalCharacter getParenthesisClassification(String s){
			if (s.matches(OPEN_PARENTHESIS)){
				return ChemicalCharacter.openParenthesis;
			}
			if (s.matches(CLOSED_PARENTHESIS)){
				return ChemicalCharacter.closedParenthesis;
			}
			else{
				return null;//in case user somehow bypasses the barricade
			}
		}
	}

	/**a field storing what chemical character came before*/
	private ChemicalCharacter beforeCharacter;
	/**a field that counts the number of parenthesis that remain open*/
	private int openParenthesisCount;
	/**a field that counts the number of letter encountered in an element*/
	private int letterCount;

	/**
	 * constants for regex of the different types of chemical characters
	 */
	public static final String LOWER_LETTER = "[a-z]";
	public static final String UPPER_LETTER = "[A-Z]";
	public static final String NUMBER = "[0-9]";
	public static final String OPEN_PARENTHESIS = "[(]";
	public static final String CLOSED_PARENTHESIS = "[)]";

	/**
	 * constant for regex of characters that are not chemically valid
	 * used to sanitize the input string
	 */
	public static final String NOT_CHEMICALLY_VALID = "[^a-zA-Z0-9(-)]";

	/**
	 * initializes ChemistrySyntaxChecker
	 * 
	 * upperLetter can place any character so that is set as initial value
	 * even if user tries to close parenthesis first,
	 * the parenthesis syntax checker separately handles that.
	 * Thus, before character is initialized as upperLetter
	 */
	public ChemicalSyntaxChecker () {
		beforeCharacter = ChemicalCharacter.upperLetter;
		openParenthesisCount = 0;
		letterCount = 0;
	}

	/**
	 * checks the syntax of an input string
	 * first the syntax is sanitized
	 * then all the characters are checked to see if they follow proper element and parenthesis rules
	 * @param s	the input string
	 * @throws IllegalElementException	thrown if an element exceeds 3 characters
	 * @throws IllegalParenthesisException	thrown if there are unclosed parenthesis or closed parenthesis when there isn't an open parenthesis
	 */
	public void checkSyntax(String s) throws IllegalElementException, IllegalParenthesisException {
		s = sanitize(s);
		for (int i = 0; i<s.length(); i++){
			processChemicalSyntax(s.charAt(i));
		}
		/**
		 * so that the very last character is processed
		 * the character input is irrelevant since it only gets saved as beforeCharacter but nothing is done to it
		 */
		processChemicalSyntax('A');
		if (openParenthesisCount>0){
			throw new IllegalParenthesisException();
		}
	}

	/**
	 * a helper method that processes the individual character syntax check
	 * takes in a character and 
	 * determines if the character is legal based on the character before it
	 * 
	 * The cases in this method update count variables after the next character
	 * @param c	the character to process
	 * @throws IllegalElementException	thrown if this character is the 4th character in an element
	 * @throws IllegalParenthesisException	thrown if this character closes parenthesis when there isn't an open parenthesis
	 */
	private void processChemicalSyntax(char c) throws IllegalElementException, IllegalParenthesisException{
		switch(beforeCharacter){
		//anything allowed

		//capital letter CH
		//lower letter Co
		//number H2
		//open parenthesis H(O)
		//close parenthesis (H)
		case upperLetter:
			upperLetterCase(c);
			break;
		//anything allowed

		//capital letter HeO
		//another letter Hea as long as there aren't more than 2 lower letter
		//number He2
		//open parenthesis He(O)
		//close parenthesis (He)
		case lowerLetter:
			lowerLetterCase(c);
			break;
		//capital letter H2O
		//number H23
		//open parenthesis H2(O)
		//close parenthesis (O2)
		case number:
			numberCase(c);
			break;
		default:
			parenthesisCase(c);
		}
	}
	
	/**
	 * the case for upper letter
	 * resets letter count to 1 since this upper letter is a letter too
	 * @param c	the current character to update the before letter for the next iteration
	 */
	private void upperLetterCase(char c) {
		letterCount=1;
		updateBeforeLetterStatus(c);
	}

	private void lowerLetterCase(char c) throws IllegalElementException {
		//post processing of lower letter count
		letterCount++;
		if (letterCount>3){
			throw new IllegalElementException();
		}
		updateBeforeLetterStatus(c);
	}

	private void numberCase(char c) throws IllegalElementException {
		String s = "" + c;
		if (s.matches(UPPER_LETTER+"|" + NUMBER+"|"+OPEN_PARENTHESIS+"|"+CLOSED_PARENTHESIS)){
			updateBeforeLetterStatus(c);
		}
		else{
			throw new IllegalElementException();
		}
	}
	
	private void parenthesisCase(char c) throws IllegalParenthesisException, IllegalElementException{
		switch(beforeCharacter){
		//capital letter (H)
		//number (3Co)
		//open parenthesis ((He))
		case openParenthesis:
			openParenthesisCase(c);
			break;
		//capital letter (He)O
		//number (He)3
		//open parenthesis (He)(Ho)
		//closed parenthesis ((He))
		case closedParenthesis:
			closedParenthesisCase(c);
			break;
		default:
			
		}
	}

	private void openParenthesisCase(char c)
			throws IllegalElementException {
		String s = "" + c;
		openParenthesisCount++;
		if (s.matches(UPPER_LETTER+"|"+NUMBER+"|"+OPEN_PARENTHESIS)){
			updateBeforeLetterStatus(c);
		}
		else{
			throw new IllegalElementException();
		}
	}
	
	private void closedParenthesisCase(char c)
			throws IllegalParenthesisException, IllegalElementException {
		String s = "" + c;
		openParenthesisCount--;
		if (openParenthesisCount<0){
			throw new IllegalParenthesisException();
		}
		if (s.matches(UPPER_LETTER+"|"+NUMBER+"|"+OPEN_PARENTHESIS+"|"+CLOSED_PARENTHESIS)){
			updateBeforeLetterStatus(c);
		}
		else{
			throw new IllegalElementException();
		}
	}
	
	private void updateBeforeLetterStatus(char c) {
		beforeCharacter = ChemicalCharacter.getCharacterClassification(c);
	}

	private String sanitize(String s) {
		ExceptionUtils.checkIllegalString(s);
		String sanitized = s;
		sanitized = sanitized.replaceAll(NOT_CHEMICALLY_VALID, "");
		return sanitized;
	}
}