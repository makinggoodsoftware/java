package com.mgs.fantasi.profile;

public interface UIPropertiesModifier<T extends PropertyDefinition<T>> {
	T apply(T border);
}
