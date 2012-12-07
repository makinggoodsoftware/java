package com.mgs.fantasi.measurements;

public class Fraction {
	private final int take;
	private final int from;

	public Fraction(int take, int from) {
		this.take = take;
		this.from = from;
	}

	public double toDouble() {
		return (double)take / (double)from;
	}

	public Fraction minus(Fraction toSubstract) {
		if (this.from != toSubstract.from) throw new RuntimeException("" +
				"Lame but I can't be bothered to implement it when the base of the fractions are different");
		return new Fraction(this.take - toSubstract.take, from);
	}

	public int getBase() {
		return from;
	}

	@Override
	public String toString() {
		return "Fraction{ "+
				from + "/" + take +
				'}';
	}
}
