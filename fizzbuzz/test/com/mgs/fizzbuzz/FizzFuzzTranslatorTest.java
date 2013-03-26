package com.mgs.fizzbuzz;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

public class FizzFuzzTranslatorTest {
	private static final int MULTIPLE_OF_THREE = 3;
	private static final int MULTIPLE_OF_FIVE = 5;

	@Mock
	private MathExpressionBuilder mathExpressionBuilder;
	@Mock
	private MathExpressionBuilder.Operation operation;
	private FizzFuzzTranslator fizzFuzzTranslator;

	@BeforeMethod
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		fizzFuzzTranslator = new FizzFuzzTranslator(mathExpressionBuilder);
	}

	@Test
	public void shouldReturnTheNumberAsStringIfNotMultipleOf3Or5() {
		addExpectation(1, MULTIPLE_OF_THREE, false);
		addExpectation(1, MULTIPLE_OF_FIVE, false);

		Assert.assertEquals("1", fizzFuzzTranslator.translate(1));
	}

	@Test
	public void shouldReturnFizzIfMultipleOf3() {
		addExpectation(3, MULTIPLE_OF_THREE, true);
		addExpectation(3, MULTIPLE_OF_FIVE, false);

		Assert.assertEquals("fizz", fizzFuzzTranslator.translate(3));
	}

	@Test
	public void shouldReturnBuzzIfMultipleOf5() {
		addExpectation(5, MULTIPLE_OF_THREE, false);
		addExpectation(5, MULTIPLE_OF_FIVE, true);

		Assert.assertEquals("buzz", fizzFuzzTranslator.translate(5));
	}

	@Test
	public void shouldReturnFizzBuzzIfMultipleOf3And5() {
		addExpectation(15, MULTIPLE_OF_THREE, true);
		addExpectation(15, MULTIPLE_OF_FIVE, true);

		Assert.assertEquals("fizzbuzz", fizzFuzzTranslator.translate(15));
	}

	private void addExpectation(int forNumber, int multiplierOf, boolean expectation) {
		when(mathExpressionBuilder.is(forNumber)).thenReturn(operation);
		when(operation.multipleOf(multiplierOf)).thenReturn(expectation);
	}


}
