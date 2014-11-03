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
	

	
	/**
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
		//sysout "T" if syntax is correct
		//sysout "F" is syntax is incorrect
		
		//care about defensive programming when writing this
		/**
		 * make sure main is the only entry point to chemistry
		 */
		//document error handling architecture
	}

}
