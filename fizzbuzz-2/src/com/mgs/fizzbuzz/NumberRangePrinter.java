package com.mgs.fizzbuzz;

import java.io.PrintStream;

public class NumberRangePrinter {
	private final NumberTranslator numberTranslator;
	private final PrintStream out;


	public NumberRangePrinter(NumberTranslator numberTranslator, PrintStream out) {
		this.out = out;
		this.numberTranslator = numberTranslator;
	}

	public void print(int low, int high) {
		for (int valueToPrint = low; valueToPrint <= high; valueToPrint++) {
			TranslationResult translationResult = numberTranslator.translate(valueToPrint);
			String toDisplay = getTextToDisplay(valueToPrint, translationResult);
			out.print(toDisplay + " ");
		}
	}

	private String getTextToDisplay(int originalValue, TranslationResult translatedValue) {
		return translatedValue.wasSuccessful() ?
				translatedValue.getTranslation() :
				String.valueOf(originalValue);
	}
}
