package com.mgs.fantasi.properties;

public interface UIProperty<T extends UIPropertyData> {
	UIProperty<T> copy();

	boolean isEmpty();

	PropertyType getType();

	T getValue();

	boolean isDefined();

	boolean isFullyDefined();

	boolean isNotDefined();
}
