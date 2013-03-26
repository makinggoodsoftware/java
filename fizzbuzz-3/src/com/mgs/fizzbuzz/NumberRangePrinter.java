package com.mgs.fizzbuzz;

import java.io.PrintStream;
import java.util.List;

public class NumberRangePrinter {
	private final NumberTranslator numberTranslator;
	private final PrintStream out;


	public NumberRangePrinter(NumberTranslator numberTranslator, PrintStream out) {
		this.numberTranslator = numberTranslator;
		this.out = out;
	}

	public void print(int low, int high) {
		NumberTranslationTransaction numberTranslationTransaction = new NumberTranslationTransaction();

		for (int valueToTranslate = low; valueToTranslate <= high; valueToTranslate++) {
			TranslationResult translationResult = numberTranslator.translate(valueToTranslate);
			String toDisplay = numberTranslationTransaction.process(valueToTranslate, translationResult);
			out.print(toDisplay + " ");
		}

		List<NumberTranslationTransaction.CountRecord> close = numberTranslationTransaction.close();

		for (NumberTranslationTransaction.CountRecord countRecord : close) {
			out.println(countRecord.getLabel() + " :" + countRecord.getCount());
		}
	}

}
