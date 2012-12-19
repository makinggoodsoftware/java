package com.mgs.fantasi.rendering;

import com.mgs.fantasi.properties.measurements.Measurement;

import static com.mgs.fantasi.properties.measurements.Measurements.simpleMeasurement;


public class Margin {

	private final Measurement top;
	private final Measurement right;
	private final Measurement bottom;
	private final Measurement left;

	public Margin(int top, int right, int bottom, int left) {
		this(simpleMeasurement(top), simpleMeasurement(right), simpleMeasurement(bottom), simpleMeasurement(left));
	}

	public Margin(Measurement top, Measurement right, Measurement bottom, Measurement left) {
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	public static Margin noMargin() {
		return new Margin(0, 0, 0, 0);
	}

	public Measurement getTop() {
		return top;
	}

	public Measurement getRight() {
		return right;
	}

	public Measurement getBottom() {
		return bottom;
	}

	public Measurement getLeft() {
		return left;
	}

	@Override
	public String toString() {
		return "Margin{" +
				"bottom=" + bottom +
				", top=" + top +
				", right=" + right +
				", left=" + left +
				'}';
	}

	public Margin withHalfOfItsSize() {
		return new Margin(top.half(), right.half(), bottom.half(), left.half());
	}

	public boolean isEmpty() {
		return top.isZero() && right.isZero() && bottom.isZero() && left.isZero();
	}

	public Margin copy() {
		return new Margin(top.copy(), right.copy(), bottom.copy(), left.copy());
	}
}
