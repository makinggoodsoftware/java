package com.mgs.fantasi.profile;

public class UIPropertiesModifierFactory {
	public static <T extends Mergeable> UIPropertiesModifier<T> ignore() {
		return new UIPropertiesKeepCurrentValueModifier<T>();
	}

	public static <T extends Mergeable> UIPropertiesModifier<T> forDefinition(T definition) {
		return new UIPropertiesModifierImpl<T> (definition);
	}

	private static class UIPropertiesKeepCurrentValueModifier<T extends Mergeable> implements UIPropertiesModifier<T> {
		@Override
		public void apply(T definition) {
			return;
		}
	}
}
