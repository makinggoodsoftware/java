package com.mgs.fantasi.profile;

public interface PropertyDefinition<T extends PropertyDefinition> {
	T merge(T original);

	boolean isDefined();

	T copy();
}
