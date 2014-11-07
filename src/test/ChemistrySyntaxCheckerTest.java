package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Test;

import chemistry.ChemistrySyntaxChecker;

public class ChemistrySyntaxCheckerTest {

	
	ChemistrySyntaxChecker c;
	
	@Before
	public void init(){
		c = new ChemistrySyntaxChecker();
		
	}
	@Test
	public void testParenthesis() {
		c.checkSyntax("(H)");
	}
	
	@Test
	public void testIllegalElement() {
		c.checkSyntax("H2o");
	}
	@Test
	public void testSpecialCharacter() {

		c.checkSyntax("$H 2");
	}
	
	@Test
	public void testRegex() {
		
	}
}
