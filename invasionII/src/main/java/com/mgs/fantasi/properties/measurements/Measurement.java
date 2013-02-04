package com.mgs.fantasi.properties.measurements;

import com.mgs.fantasi.properties.Padding;
import com.mgs.fantasi.properties.UIPropertyData;

public interface Measurement extends UIPropertyData {
	public Padding asPadding();

	Measurement half();

	boolean isZero();

	Measurement copy();
}
