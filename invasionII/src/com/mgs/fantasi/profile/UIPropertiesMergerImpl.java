package com.mgs.fantasi.profile;

public class UIPropertiesMergerImpl<T extends PropertyDefinition<T>> implements UIPropertiesMerger<T> {
	@Override
	public T with(T original, T modifier) {
		if (modifier == null) throw new IllegalArgumentException("The modifier in a merge operation can't be null");
		return modifier.copy();
	}
}
