package com.mgs.fizzbuzz;

import java.io.PrintStream;

import static com.mgs.fizzbuzz.FizzFuzzTranslator.createFizzFuzzTranslator;

public class Main {
	public static void main(String... args) {
		FizzFuzzTranslator numberTranslator = createFizzFuzzTranslator(new MathExpertImpl());
		PrintStream printStream = System.out;

		new Main().go(numberTranslator, printStream, 1, 20);
	}

	private void go(FizzFuzzTranslator numberTranslator, PrintStream printStream, int low, int high) {
		new NumberRangePrinter(numberTranslator, printStream).print(low, high);
	}
}
