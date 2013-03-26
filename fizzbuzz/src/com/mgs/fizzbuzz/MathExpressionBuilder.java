package com.mgs.fizzbuzz;

/**
 * This is an utility class to avoid calling directly into
 * MathExpert.isMultipleOf (a, b)
 * <p/>
 * It just shuffles things around so the code reads much better
 * <p/>
 * ie
 * MathExpressionBuilder.is(a).multipleOf(b)
 */
public class MathExpressionBuilder {
	private final MathExpertImpl mathExpert;

	public MathExpressionBuilder(MathExpertImpl mathExpert) {
		this.mathExpert = mathExpert;
	}

	public Operation is(int on) {
		return new Operation(on);
	}

	public class Operation {
		private final int on;

		public Operation(int on) {
			this.on = on;
		}

		public boolean multipleOf(int of) {
			return mathExpert.isMultipleOf(on, of);
		}
	}
}
