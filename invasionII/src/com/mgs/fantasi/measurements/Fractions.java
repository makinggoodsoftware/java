package com.mgs.fantasi.measurements;

public class Fractions {
	public static Fraction thwoThirds() {
		return new Fraction(2, 3);
	}

	public static Fraction oneThird() {
		return new Fraction(1, 3);
	}

	public static Fraction all() {
		return new Fraction(1, 1);
	}

	public static Fraction allWithBase(int base) {
		return new Fraction(base, base);
	}

	public static Fraction half() {
		return new Fraction(1, 2);
	}
}
