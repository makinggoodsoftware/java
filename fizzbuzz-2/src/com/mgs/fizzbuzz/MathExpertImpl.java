package com.mgs.fizzbuzz;

public class MathExpertImpl implements MathExpert {
	@Override
	public boolean contains(int numberWeAreLookingAt, int digitToContain) {
		String numberWeAreLookingAtAsString = String.valueOf(numberWeAreLookingAt);
		String numberToLookForAsString = String.valueOf(digitToContain);

		return numberWeAreLookingAtAsString.indexOf(numberToLookForAsString) >= 0;
	}

	@Override
	public boolean isMultipleOf(int i, int of) {
		return i % of == 0;
	}
}
