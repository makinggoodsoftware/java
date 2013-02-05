package com.mgs.fantasi.properties.data;

import com.mgs.fantasi.properties.data.measurements.Measurement;
import org.apache.commons.lang.builder.EqualsBuilder;

import static com.mgs.fantasi.properties.data.measurements.Measurements.simpleMeasurement;


public class Padding implements UIPropertyData {

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

	public static Padding zeroPadding() {
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

		return EqualsBuilder.reflectionEquals(this, o);
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
