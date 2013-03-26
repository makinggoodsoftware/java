package com.mgs.fizzbuzz;

public interface NumberTranslationRule {
	boolean appliesTo(int toTranslate);

	String translate(int toTranslate);
}
