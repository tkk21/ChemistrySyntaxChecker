package test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import chemistry.ChemistrySyntaxChecker;
import chemistry.IllegalElementException;
import chemistry.IllegalParenthesisException;

public class ChemistrySyntaxCheckerTest {

	
	ChemistrySyntaxChecker c;
	
	@Before
	public void init(){
		c = new ChemistrySyntaxChecker();
		
	}
	@Test
	public void testParenthesis() throws IllegalElementException, IllegalParenthesisException {
		c.checkSyntax("(H)");
	}
	
	@Test
	public void testIllegalElement_NumberCase() throws IllegalElementException, IllegalParenthesisException {
		c.checkSyntax("H2o");
	}
	
	@Test
	public void testSpecialCharacter() throws IllegalElementException, IllegalParenthesisException {

		c.checkSyntax("$H 2");
	}
	
	@Test
	public void testRegex() {
		
	}
	
	@Test
	public void testEdge() throws IllegalElementException, IllegalParenthesisException {
		c.checkSyntax("Cooo");
	}
}
