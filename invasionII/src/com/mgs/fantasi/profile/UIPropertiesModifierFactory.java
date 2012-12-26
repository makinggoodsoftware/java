package com.mgs.fantasi.profile;

public class UIPropertiesModifierFactory {
	public static <T extends PropertyDefinition<T>> UIPropertiesMerger<T> ignore() {
		return new UIPropertiesKeepCurrentValueMerger<T>();
	}

	public static <T extends PropertyDefinition<T>> UIPropertiesMerger<T> forDefinition(T definition) {
		return new UIPropertiesMergerImpl<T>(definition);
	}

	private static class UIPropertiesKeepCurrentValueMerger<T extends PropertyDefinition<T>> implements UIPropertiesMerger<T> {
		@Override
		public T merge(T original, T modifier) {
			return modifier;
		}
	}
}
