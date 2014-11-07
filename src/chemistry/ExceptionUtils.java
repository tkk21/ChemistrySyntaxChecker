package chemistry;

/**
 * a class with static methods to check for exceptions
 * 
 * This is the only place where F will be printed to console
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
			Chemistry.failChemistry();
		}
		if (s.length()==0){
			Chemistry.failChemistry();
		}
	}
}
