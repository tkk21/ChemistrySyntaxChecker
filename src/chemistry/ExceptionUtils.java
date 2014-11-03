package chemistry;

/**
 * a class with static methods to check for exceptions
 * @author Ted Kim
 * @email tkk21@case.edu
 *
 */

public class ExceptionUtils {

	public static void checkNulls (Object ... args){
		if (args == null){
			throw new NullPointerException();
		}
		for (Object o: args){
			if (o == null){
				throw new NullPointerException();
			}
		}
	}
	
	public static void checkIllegalString (String s){
		try{
			ExceptionUtils.checkNulls(s);//throw NPE if input is null
		}
		catch(NullPointerException e){
			failChemistry();
		}
		if (s.length()==0){
			failChemistry();
		}
	}
	
	/**
	 * a possible room for hacking
	 * is this a safe thing to do?
	 * 
	 * can someone prematurely exit the program using this?  
	 */
	private static void failChemistry (){
		System.out.println("F");
		System.exit(0);//what status to exit with?
	}
}
