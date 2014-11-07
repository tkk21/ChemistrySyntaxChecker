package chemistry;


public class ChemistrySyntaxChecker {

	public enum ChemistryCharacterStatus{
		openParenthesis, closedParenthesis, 
		upperLetter, lowerLetter, number;


		public static ChemistryCharacterStatus getCharacterClassification(char c){
			String s = ""+c;
			if (s.matches(UPPER_LETTER)){
				return ChemistryCharacterStatus.upperLetter;
			}
			if (s.matches(LOWER_LETTER)){
				return ChemistryCharacterStatus.lowerLetter;
			}
			if (s.matches(NUMBER)){
				return ChemistryCharacterStatus.number;
			}
			else {
				return getParenthesisClassification(s);
			}

		}

		private static ChemistryCharacterStatus getParenthesisClassification(String s){
			if (s.matches(OPEN_PARENTHESIS)){
				return ChemistryCharacterStatus.openParenthesis;
			}
			if (s.matches(CLOSED_PARENTHESIS)){
				return ChemistryCharacterStatus.closedParenthesis;
			}
			else{
				return null;//in case user somehow bypasses the barricade
			}
		}
	}

	private ChemistryCharacterStatus beforeCharacter;
	private int parenthesisCount;
	private int letterCount;

	public static final String LOWER_LETTER = "[a-z]";
	public static final String UPPER_LETTER = "[A-Z]";
	public static final String NUMBER = "[0-9]";
	public static final String OPEN_PARENTHESIS = "[(]";
	public static final String CLOSED_PARENTHESIS = "[)]";

	public static final String CHEMICALLY_VALID = "[a-zA-Z0-9(-)]";
	public static final String NOT_CHEMICALLY_VALID = "[^a-zA-Z0-9(-)]";


	/**
	 * 
	 * upperLetter can place any character so that is set as initial value
	 * even if user tries to close parenthesis first,
	 * the parenthesis syntax checker separately handles that
	 * 
	 */
	public ChemistrySyntaxChecker () {
		beforeCharacter = ChemistryCharacterStatus.upperLetter;
		parenthesisCount = 0;
		letterCount = 0;
	}

	public void checkSyntax(String s) {
		s = sanitize(s);

		for (int i = 0; i<s.length(); i++){
			try {
				processChemistrySyntax(s.charAt(i));
			} 
			catch (IllegalElementException e) {
				ExceptionUtils.failChemistry();
			}
			catch (IllegalParenthesisException e){
				ExceptionUtils.failChemistry();
			}
		}
		if (parenthesisCount>0){
			try {
				throw new IllegalParenthesisException();
			} 
			catch (IllegalParenthesisException e) {
				ExceptionUtils.failChemistry();
			}
		}
	}


	private void processChemistrySyntax(char c) throws IllegalElementException, IllegalParenthesisException{
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

	
	
	private void upperLetterCase(char c) {
		updateBeforeLetterStatus(c);
	}

	private void lowerLetterCase(char c) throws IllegalElementException {
		//post processing of lower letter count
		letterCount++;
		if (letterCount>2){
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
		parenthesisCount++;
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
		parenthesisCount--;
		if (parenthesisCount<0){
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
		beforeCharacter = ChemistryCharacterStatus.getCharacterClassification(c);
	}

	private void checkParenthesis (char c){
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


