package com.mgs.fizzbuzz;

import java.io.PrintStream;

import static com.mgs.fizzbuzz.FizzFuzzRule.fizzFuzzRule;
import static com.mgs.fizzbuzz.LuckyRule.luckyRule;

public class Main {
	public static void main(String... args) {
		MathExpertImpl mathExpert = new MathExpertImpl();
		NumberTranslator numberTranslator = new RuleBasedNumberTranslator(luckyRule(mathExpert), fizzFuzzRule(mathExpert));
		PrintStream printStream = System.out;

		new Main().go(numberTranslator, printStream, 1, 20);
	}

	private void go(NumberTranslator numberTranslator, PrintStream printStream, int low, int high) {
		new NumberRangePrinter(numberTranslator, printStream).print(low, high);
	}
}
