package com.mgs.fantasi.properties;

import com.mgs.fantasi.properties.data.UIPropertyData;

public interface UIProperty<T extends UIPropertyData> {
	boolean isEmpty();

	boolean isDefined();

	boolean isFullyDefined();

	boolean isFullyUndefined();

	T getValue();
}
