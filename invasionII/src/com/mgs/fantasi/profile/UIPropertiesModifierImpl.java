package com.mgs.fantasi.profile;

public class UIPropertiesModifierImpl<T extends PropertyDefinition<T>> implements UIPropertiesModifier<T> {
	private final T definition;

	public UIPropertiesModifierImpl(T definition) {
		this.definition = definition;
	}

	@Override
	public T apply(T into) {
		return definition.produce();
	}
}
