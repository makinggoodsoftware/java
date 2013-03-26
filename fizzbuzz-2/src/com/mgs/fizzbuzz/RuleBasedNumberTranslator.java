package com.mgs.fizzbuzz;

import static com.mgs.fizzbuzz.TranslationResult.failure;
import static com.mgs.fizzbuzz.TranslationResult.successful;

public class RuleBasedNumberTranslator implements NumberTranslator {
	private final NumberTranslationRule[] rules;

	public RuleBasedNumberTranslator(NumberTranslationRule... rules) {
		this.rules = rules;
	}

	@Override
	public TranslationResult translate(int toTranslate) {
		for (NumberTranslationRule rule : rules) {
			if (rule.appliesTo(toTranslate)) {
				return successful(rule.translate(toTranslate));
			}
		}
		return failure();
	}

	public NumberTranslationRule[] getRules() {
		return rules;
	}
}
