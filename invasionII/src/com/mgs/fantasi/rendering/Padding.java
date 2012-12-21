package com.mgs.fantasi.rendering;

import com.mgs.fantasi.properties.measurements.Measurement;

import static com.mgs.fantasi.properties.measurements.Measurements.simpleMeasurement;


public class Padding {

	private final Measurement top;
	private final Measurement right;
	private final Measurement bottom;
	private final Measurement left;

	public Padding(int top, int right, int bottom, int left) {
		this(simpleMeasurement(top), simpleMeasurement(right), simpleMeasurement(bottom), simpleMeasurement(left));
	}

	public Padding(Measurement top, Measurement right, Measurement bottom, Measurement left) {
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	public static Padding noPadding() {
		return new Padding(0, 0, 0, 0);
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
		return "Padding{" +
				"bottom=" + bottom +
				", top=" + top +
				", right=" + right +
				", left=" + left +
				'}';
	}

	public Padding withHalfOfItsSize() {
		return new Padding(top.half(), right.half(), bottom.half(), left.half());
	}

	public boolean isEmpty() {
		return top.isZero() && right.isZero() && bottom.isZero() && left.isZero();
	}

	public Padding copy() {
		return new Padding(top.copy(), right.copy(), bottom.copy(), left.copy());
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (!(o instanceof Padding)) return false;

		Padding padding = (Padding) o;

		if (bottom != null ? !bottom.equals(padding.bottom) : padding.bottom != null) return false;
		if (left != null ? !left.equals(padding.left) : padding.left != null) return false;
		if (right != null ? !right.equals(padding.right) : padding.right != null) return false;
		if (top != null ? !top.equals(padding.top) : padding.top != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = top != null ? top.hashCode() : 0;
		result = 31 * result + (right != null ? right.hashCode() : 0);
		result = 31 * result + (bottom != null ? bottom.hashCode() : 0);
		result = 31 * result + (left != null ? left.hashCode() : 0);
		return result;
	}
}
