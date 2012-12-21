package com.mgs.fantasi.profile;

public class UIPropertiesModifierFactory {
	public static <T extends PropertyDefinition<T>> UIPropertiesModifier<T> ignore() {
		return new UIPropertiesKeepCurrentValueModifier<T>();
	}

	public static <T extends PropertyDefinition<T>> UIPropertiesModifier<T> forDefinition(T definition) {
		return new UIPropertiesModifierImpl<T> (definition);
	}

	private static class UIPropertiesKeepCurrentValueModifier<T extends PropertyDefinition<T>> implements UIPropertiesModifier<T> {
		@Override
		public T apply(T definition) {
			return definition;
		}
	}
}
