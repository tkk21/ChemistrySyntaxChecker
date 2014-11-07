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
	 */
	
	
	@Test
	public void testParenthesis() throws IllegalElementException, IllegalParenthesisException {
		c.checkSyntax("(H)");
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
		fail("should have thrown an illegal element exception");
		}
		catch(IllegalElementException e){
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
