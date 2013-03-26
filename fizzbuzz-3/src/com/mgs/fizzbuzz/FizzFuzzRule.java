package com.mgs.fizzbuzz;

public class FizzFuzzRule implements NumberTranslationRule {
	private final MathExpressionBuilder mathExpressionBuilder;

	/**
	 * Wrapping the fact that we use an expression builder to make the code easier to
	 * read, from the outside, we just specify the Math expert implementation that we
	 * want to use to check whether the numbers are multiples or not, then this is
	 * wrapped automatically into an expression builder
	 *
	 * @param mathExpert
	 * @return
	 */
	public static FizzFuzzRule fizzFuzzRule(MathExpertImpl mathExpert) {
		return new FizzFuzzRule(new MathExpressionBuilder(mathExpert));
	}

	FizzFuzzRule(MathExpressionBuilder mathExpressionBuilder) {
		this.mathExpressionBuilder = mathExpressionBuilder;
	}

	@Override
	public boolean appliesTo(int toTranslate) {
		boolean multipleOf3 = mathExpressionBuilder.is(toTranslate).multipleOf(3);
		boolean multipleOf5 = mathExpressionBuilder.is(toTranslate).multipleOf(5);

		return multipleOf3 || multipleOf5;
	}

	@Override
	public String translate(int toTranslate) {
		boolean multipleOf3 = mathExpressionBuilder.is(toTranslate).multipleOf(3);
		boolean multipleOf5 = mathExpressionBuilder.is(toTranslate).multipleOf(5);

		if (multipleOf3 && multipleOf5) return "fizzbuzz";
		if (multipleOf3) return "fizz";
		if (multipleOf5) return "buzz";

		throw new IllegalArgumentException("Should never reach this point of code");
	}

}
