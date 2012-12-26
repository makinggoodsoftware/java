package com.mgs.fantasi.profile;

public interface UIPropertiesMerger<T extends PropertyDefinition<T>> {
	T with(T original, T modifier);
}
