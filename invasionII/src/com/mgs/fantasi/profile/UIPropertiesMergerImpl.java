package com.mgs.fantasi.profile;

public class UIPropertiesMergerImpl<T extends PropertyDefinition<T>> implements UIPropertiesMerger<T> {
	private final T definition;

	public UIPropertiesMergerImpl(T definition) {
		this.definition = definition;
	}

	@Override
	public T merge(T original, T modifier) {
		return definition.produce();
	}
}
