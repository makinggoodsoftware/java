package com.mgs.fizzbuzz;

public class MathExpertImpl implements MathExpert {
	@Override
	public boolean isMultipleOf(int i, int of) {
		return i % of == 0;
	}
}
