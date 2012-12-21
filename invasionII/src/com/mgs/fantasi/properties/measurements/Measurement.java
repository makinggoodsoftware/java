package com.mgs.fantasi.properties.measurements;

import com.mgs.fantasi.rendering.Padding;

public interface Measurement {
	public Padding asPadding();

	Measurement half();

	boolean isZero();

	Measurement copy();

	;
}
