package com.mgs.fizzbuzz;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.mgs.fizzbuzz.LuckyRule.luckyRule;

public class LuckyRuleTest {

	private static final String DELEGATED_TRANSLATION = "1";
	private LuckyRule luckyRule;
	@Mock
	private FizzFuzzRule fizzFuzzRule;
	@Mock
	private MathExpertImpl mathExpert;

	@BeforeMethod
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		luckyRule = luckyRule(mathExpert);
	}

	@Test
	public void shouldAlwaysTranslateToLucky() {
		assertEquals("lucky", luckyRule.translate(3));
		assertEquals("lucky", luckyRule.translate(1));
		assertEquals("lucky", luckyRule.translate(2));
		assertEquals("lucky", luckyRule.translate(6));
	}


}
