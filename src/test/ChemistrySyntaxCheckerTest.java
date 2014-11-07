package test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import chemistry.ChemicalSyntaxChecker;
import chemistry.IllegalElementException;
import chemistry.IllegalParenthesisException;

public class ChemistrySyntaxCheckerTest {

	
	ChemicalSyntaxChecker c;
	
	@Before
	public void init(){
		c = new ChemicalSyntaxChecker();
	}
	
	/**
	 * cases
	 * 1. single element
	 * H
	 * 1a. single element with length 2
	 * He
	 * 1b. single element with length 3
	 * Heh
	 * 2. single element with coefficient
	 * H3
	 * 3. single element within parenthesis
	 * (H)
	 * 4. single element within parenthesis with coefficient
	 * (H)2
	 * 5. single element with coefficient within parenthesis with coefficient
	 * (H2)4 
	 * 6. single element with number in front of it
	 * 4H
	 * @throws IllegalParenthesisException 
	 * @throws IllegalElementException 
	 */

	@Test
	public void testSingleElement() throws IllegalElementException, IllegalParenthesisException{
		try{
			c.checkSyntax("c");
		}
		catch(Exception e){
			fail();
		}
	}
	@Test
	public void testParenthesis() throws IllegalElementException, IllegalParenthesisException {
		try{
			c.checkSyntax("(H)");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testIllegalElement_NumberCase() throws IllegalElementException, IllegalParenthesisException {
		try{
			c.checkSyntax("H2o");
			fail("should have thrown an illegal element exception");
		}
		catch(IllegalElementException e){
		}
	}
	
	@Test
	public void testSpecialCharacter() throws IllegalElementException, IllegalParenthesisException {
		try{
			c.checkSyntax("$H 2");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testRegex() {
		
	}
	
	@Test
	public void testEdge() throws IllegalElementException, IllegalParenthesisException {
		try{
			c.checkSyntax("Cooo");
			fail("should have thrown an illegal element exception");
		}
		catch(IllegalElementException e){
		}
	}
}
