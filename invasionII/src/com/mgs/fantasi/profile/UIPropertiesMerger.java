package com.mgs.fantasi.profile;

public interface UIPropertiesMerger<T extends PropertyDefinition<T>> {
	T merge(T original, T modifier);
}
