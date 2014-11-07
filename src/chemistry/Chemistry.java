package chemistry;

/**
 * hw10
 * 
 * the main point of entry
 * takes in an input string and prints T
 * if the chemical syntax is correct
 * else prints out F
 * @author ted
 *
 */
public class Chemistry {

	/**
	 * the main method where the input goes through the syntax checker
	 * if there is no input, then F is printed
	 * @param args
	 */

	public static void main(String [] args){
		if (args.length<1){
			failChemistry();
			return;
		}
		ChemicalSyntaxChecker check = new ChemicalSyntaxChecker();
		try {
			check.checkSyntax(args[0]);
		} catch (IllegalElementException e) {
			failChemistry();
			return;
		} catch (IllegalParenthesisException e) {
			failChemistry();
			return;
		}
		System.out.println("T");//went through the syntax check without failing
	}
	
	/**
	 * the routine for exiting the program due to failure
	 */
	public static void failChemistry() {
		System.out.println("F");
		System.exit(0);
	}
}
