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
		for (int i = low; i <= high; i++) {
			out.print(numberTranslator.translate(i) + " ");
		}
	}
}
