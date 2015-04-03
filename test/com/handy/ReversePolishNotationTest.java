package com.handy;

import static com.handy.ReversePolishNotation.calculateOutput;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("static-method")
public class ReversePolishNotationTest {

	@Test
	public void testDivisionByZero() {
		Assert.assertEquals(0, calculateOutput("2 0 /"), 0);
	}

	@Test
	public void testInvalidOperator() {
		Assert.assertEquals(0, calculateOutput("2 5 x"), 0);
	}

	@Test
	public void testInsufficientOperands() {
		Assert.assertEquals(0, calculateOutput("2 +"), 0);
	}

	@Test
	public void testExtraOperands() {
		Assert.assertEquals(0, calculateOutput("2 4 + 6"), 0);
	}

	@Test
	public void testValidCalculation1() {
		Assert.assertEquals(0, calculateOutput("2 3 + 5 + 10 -"), 0);
	}

	@Test
	public void testValidCalculation2() {
		Assert.assertEquals(11, calculateOutput("2 2 2 * * 3 +"), 0);
	}

	@Test
	public void testValidCalculation3() {
		Assert.assertEquals(-14.5, calculateOutput("5 1 2 / 4 - * 3 +"), 0);
	}

	@Test
	public void testValidCalculation4() {
		Assert.assertEquals(9, calculateOutput("3 2 1 + *"), 0);
	}

	@Test
	public void testValidCalculation5() {
		Assert.assertEquals(35, calculateOutput("3 4 + 5 *"), 0);
	}
}
