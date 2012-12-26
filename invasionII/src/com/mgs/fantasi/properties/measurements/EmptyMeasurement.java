package com.mgs.fantasi.properties.measurements;

import com.mgs.fantasi.rendering.Padding;

public class EmptyMeasurement implements Measurement{
	public static final EmptyMeasurement EMPTY_MEASUREMENT = new EmptyMeasurement();

	public static EmptyMeasurement emptyMeasurement (){
		return EMPTY_MEASUREMENT;
	}

	private EmptyMeasurement() {
	}

	@Override
	public Padding asPadding() {
		return Padding.zero();
	}

	@Override
	public Measurement half() {
		return new com.mgs.fantasi.properties.measurements.EmptyMeasurement();
	}

	@Override
	public boolean isZero() {
		return true;
	}

	@Override
	public Measurement copy() {
		return new com.mgs.fantasi.properties.measurements.EmptyMeasurement();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		return o instanceof EmptyMeasurement;
	}
}
