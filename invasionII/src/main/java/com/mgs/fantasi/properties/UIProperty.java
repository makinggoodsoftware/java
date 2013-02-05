package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.UIPropertyData;

public interface UIProperty<T extends UIPropertyData> {
	UIProperty<T> copy();

	boolean isEmpty();

	T getValue();

	boolean isDefined();

	boolean isFullyDefined();

	boolean isNotDefined();
}
