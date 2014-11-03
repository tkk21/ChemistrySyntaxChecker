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
}
