package com.mgs.fantasi.measurements;

import com.mgs.fantasi.ui.wireframe.Margin;

public interface Measurement {
	public Margin asMargin();

	Measurement half();

	boolean isZero();

	Measurement copy();
}
