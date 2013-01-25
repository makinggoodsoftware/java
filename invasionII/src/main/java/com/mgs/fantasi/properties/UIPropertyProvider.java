package com.mgs.fantasi.properties;

public interface UIPropertyProvider<Z extends UIProperty> {
	UIPropertyProvider<Z> filterStronger(UIPropertyProvider<Z> toApply);

	boolean isDefined();

	Z getValue();
}
