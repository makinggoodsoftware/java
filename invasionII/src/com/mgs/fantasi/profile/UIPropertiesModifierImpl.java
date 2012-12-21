package com.mgs.fantasi.profile;

public class UIPropertiesModifierImpl<T extends Mergeable> implements UIPropertiesModifier<T> {
	private final T definition;

	public UIPropertiesModifierImpl(T definition) {
		this.definition = definition;
	}

	@Override
	public void apply(T into) {
		definition.merge(into);
	}
}
