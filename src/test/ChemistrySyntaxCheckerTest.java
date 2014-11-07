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
	
	@Test
	public void testSingleElement_len1() throws IllegalElementException, IllegalParenthesisException{
		try{
			c.checkSyntax("C");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_len1_fail() throws IllegalElementException, IllegalParenthesisException{
		try{
			c.checkSyntax("c");
			fail("should have thrown an exception");
		}
		catch(IllegalElementException e){
		}
	}
	
	@Test
	public void testSingleElement_len2(){
		try{
			c.checkSyntax("He");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_len3(){
		try{
			c.checkSyntax("Heh");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_numberInFront(){
		try{
			c.checkSyntax("4H");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_numberInFrontLarge(){
		try{
			c.checkSyntax("99999999999999999H");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_coefficient(){
		try{
			c.checkSyntax("H9");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_coefficientLarge(){
		try{
			c.checkSyntax("H90000000");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_withinParenthesis(){
		try{
			c.checkSyntax("(C)");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_withinParenthesis_thatHasCoefficient(){
		try{
			c.checkSyntax("(C)3");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_withinParenthesis_thatHasCoefficient_large(){
		try{
			c.checkSyntax("(C)333333333333333");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_coefficient_withinParenthesis_thatHasCoefficient(){
		try{
			c.checkSyntax("(C4)3");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testSingleElement_coefficient_withinParenthesis_thatHasCoefficient_large(){
		try{
			c.checkSyntax("(C44444)333333");
		}
		catch(Exception e){
			fail();
		}
	}

	@Test
	public void testMultipleElement_multipleSameElement(){
		try{
			c.checkSyntax("CCCC");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testMultipleElement_multipleDifferentElement(){
		try{
			c.checkSyntax("QWERTY");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testMultipleElement_withNumbersInBetween(){
		try{
			c.checkSyntax("8Q3W5E8R5T35Y");
		}
		catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testMultipleElement_withNumbersInBetween_large(){
		try{
			c.checkSyntax("8123412Q312412W521412E821412R512412T3124125Y");
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
	public void testUnclosedParenthesis() throws IllegalElementException{
		try{
			c.checkSyntax("((H)");
			fail("uncaught illegal parenthesis exception");
		}
		catch(IllegalParenthesisException e){
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
