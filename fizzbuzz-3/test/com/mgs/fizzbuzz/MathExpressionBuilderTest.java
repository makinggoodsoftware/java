package com.mgs.fizzbuzz;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MathExpressionBuilderTest {
	private static final int A_NUMBER = 1;
	private static final int ANOTHER_NUMBER = 2;
	@Mock
	private MathExpertImpl mathExpert;

	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldDelegateIntoMathExpert() {
		new MathExpressionBuilder(mathExpert).is(A_NUMBER).multipleOf(ANOTHER_NUMBER);

		Mockito.verify(mathExpert).isMultipleOf(A_NUMBER, ANOTHER_NUMBER);
	}
}
