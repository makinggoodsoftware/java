package com.mgs.fantasi.structures;

public class Fraction {
	private final int take;
	private final int from;

	public Fraction(int take, int from) {
		this.take = take;
		this.from = from;
	}

	public double toDouble() {
		return take / from;
	}
}
