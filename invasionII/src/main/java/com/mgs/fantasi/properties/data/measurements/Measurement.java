package com.mgs.fantasi.properties.data.measurements;

import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.UIPropertyData;

public interface Measurement extends UIPropertyData {
	public Padding asPadding();

	Measurement half();

	boolean isZero();

	Measurement copy();
}
