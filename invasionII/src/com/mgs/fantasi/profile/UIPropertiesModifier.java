package com.mgs.fantasi.profile;

public interface UIPropertiesModifier<T extends Mergeable> {
	void apply(T border);
}
