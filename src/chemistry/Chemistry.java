package chemistry;

/**
 * hw10
 * 
 * @author ted
 *
 */
public class Chemistry {

	//defensive programming so be really careful

	//not required to test

	//watch out for exceptions being thrown in the constructor

	public static void parseChemistry(String s){
		ExceptionUtils.checkIllegalString(s);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<s.length(); i++){
			
			sb.append(s.charAt(i));
			
		}
	}
	/**
	 * finds whether an element name is valid or not
	 * Valid element names have
	 * zero to two more lower case letters
	 * 
	 * so Three characters in total allowed
	 * Hello <- not valid
	 * Jar <- valid
	 * @param s
	 * @return
	 */
	public static boolean isElement(String s){
		if (s.length()>3){
			return false;
		}
		if (!Character.isUpperCase(s.charAt(0))){
			return false;
		}
		for (int i = 1; i<s.length(); i++){
			if (Character.isUpperCase(s.charAt(i))){
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param args
	 */

	public static void main(String [] args){
		/**
		 * sanitize the input before using it in the code
		 * 
		 * some error handling architecture
		 * 
		 * if the argument has some spaces, simply remove them
		 * 
		 * if the entire input string cannot be split into individual elements, sysout F
		 * then throw illegal element exception
		 * 
		 * if there is an unclosed parenthesis or closed parenthesis without opening,
		 * sysout F then throw illegal parenthesis exception
		 * 
		 * 
		 */
		//sysout "T" if syntax is correct
		//sysout "F" is syntax is incorrect

		//care about defensive programming when writing this
		/**
		 * make sure main is the only entry point to chemistry
		 */
		//document error handling architecture
	}

}
