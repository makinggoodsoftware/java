package com.mgs.fizzbuzz;

import java.io.PrintStream;

import static com.mgs.fizzbuzz.FizzFuzzRule.fizzFuzzRule;
import static com.mgs.fizzbuzz.LuckyRule.luckyRule;

public class Main {
	public static void main(String... args) {
		MathExpertImpl mathExpert = new MathExpertImpl();

		NumberTranslator numberTranslator = new RuleBasedNumberTranslator(luckyRule(mathExpert), fizzFuzzRule(mathExpert));

		new Main().go(numberTranslator, System.out, 1, 100);
	}

	private void go(NumberTranslator numberTranslator, PrintStream out, int low, int high) {
		new NumberRangePrinter(numberTranslator, out).print(low, high);
	}
}
