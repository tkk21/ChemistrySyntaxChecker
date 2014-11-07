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
		if (args.length<1){
			failChemistry();
			return;
		}
		ChemistrySyntaxChecker check = new ChemistrySyntaxChecker();
		check.checkSyntax(args[0]);
	}
	
	/**
	 * a possible room for hacking
	 * is this a safe thing to do?
	 * 
	 * can someone prematurely exit the program using this?  
	 */
	public static void failChemistry() {
		System.out.println("F");
		System.exit(0);
	}
}
