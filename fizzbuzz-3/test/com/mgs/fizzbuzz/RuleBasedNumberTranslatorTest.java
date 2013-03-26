package com.mgs.fizzbuzz;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RuleBasedNumberTranslatorTest {
	private static final String RULE_2_TRANSLATION_RESULT = "lelele";
	private static final String RULE_1_TRANSLATION_RESULT = "lalala";
	@Mock
	private NumberTranslationRule rule1;
	@Mock
	private NumberTranslationRule rule2;

	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldKeepTheOrderOfTheRules() {
		RuleBasedNumberTranslator ruleBasedNumberTranslatorTest = new RuleBasedNumberTranslator(rule1, rule2);

		Assert.assertEquals(rule1, ruleBasedNumberTranslatorTest.getRules()[0]);
		Assert.assertEquals(rule2, ruleBasedNumberTranslatorTest.getRules()[1]);
	}

	@Test
	public void shouldReturnFailureIfNoRuleApplies() {
		Mockito.when(rule1.appliesTo(1)).thenReturn(false);
		Mockito.when(rule2.appliesTo(1)).thenReturn(false);

		RuleBasedNumberTranslator ruleBasedNumberTranslatorTest = new RuleBasedNumberTranslator(rule1, rule2);

		TranslationResult translation = ruleBasedNumberTranslatorTest.translate(1);
		Assert.assertFalse(translation.wasSuccessful());
	}

	@Test
	public void shouldApplyRule1() {
		Mockito.when(rule1.appliesTo(1)).thenReturn(true);
		Mockito.when(rule2.appliesTo(1)).thenReturn(false);
		Mockito.when(rule1.translate(1)).thenReturn(RULE_1_TRANSLATION_RESULT);

		RuleBasedNumberTranslator ruleBasedNumberTranslatorTest = new RuleBasedNumberTranslator(rule1, rule2);

		TranslationResult translation = ruleBasedNumberTranslatorTest.translate(1);
		Assert.assertTrue(translation.wasSuccessful());
		Assert.assertEquals(RULE_1_TRANSLATION_RESULT, translation.getTranslation());
	}

	@Test
	public void shouldApplyRule2() {
		Mockito.when(rule1.appliesTo(1)).thenReturn(false);
		Mockito.when(rule2.appliesTo(1)).thenReturn(true);
		Mockito.when(rule2.translate(1)).thenReturn(RULE_2_TRANSLATION_RESULT);

		RuleBasedNumberTranslator ruleBasedNumberTranslatorTest = new RuleBasedNumberTranslator(rule1, rule2);

		TranslationResult translation = ruleBasedNumberTranslatorTest.translate(1);
		Assert.assertTrue(translation.wasSuccessful());
		Assert.assertEquals(RULE_2_TRANSLATION_RESULT, translation.getTranslation());
	}
}
