package com.mgs.fantasi.properties.measurements;

import com.mgs.fantasi.rendering.Margin;

public interface Measurement {
	public Margin asMargin();

	Measurement half();

	boolean isZero();

	Measurement copy();
}
