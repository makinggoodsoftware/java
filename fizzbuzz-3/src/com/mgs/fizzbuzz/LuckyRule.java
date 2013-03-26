package com.mgs.fizzbuzz;

public class LuckyRule implements NumberTranslationRule {
	private final MathExpressionBuilder mathExpressionBuilder;

	static LuckyRule luckyRule(MathExpert mathExpert) {
		return new LuckyRule(new MathExpressionBuilder(mathExpert));
	}

	LuckyRule(MathExpressionBuilder mathExpressionBuilder) {
		this.mathExpressionBuilder = mathExpressionBuilder;
	}

	@Override
	public boolean appliesTo(int toTranslate) {
		return mathExpressionBuilder.is(toTranslate).containingTheDigit(3);
	}

	@Override
	public String translate(int toTranslate) {
		return "lucky";
	}
}
