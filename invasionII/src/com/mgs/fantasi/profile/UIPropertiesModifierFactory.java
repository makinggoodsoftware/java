package com.mgs.fantasi.profile;

public class UIPropertiesModifierFactory {
	public static <T extends PropertyDefinition<T>> UIPropertiesMerger<T> ignore() {
		return new UIPropertiesKeepCurrentValueMerger<T>();
	}

	public static <T extends PropertyDefinition<T>> UIPropertiesMergerBuilder<T> merge(T definition) {
		if (definition!=null){
			return new UIPropertiesMergerBuilder<T>(definition, new UIPropertiesMergerImpl<T>());
		}
		UIPropertiesMerger<T> ignore = ignore();
		return new UIPropertiesMergerBuilder<T>(definition, ignore);
	}

	public static <T extends PropertyDefinition<T>> UIPropertiesMerger<T> mergeOld(T definition) {
		if (definition!=null){
			return new UIPropertiesMergerImpl<T>();
		}
		return ignore();
	}


	private static class UIPropertiesKeepCurrentValueMerger<T extends PropertyDefinition<T>> implements UIPropertiesMerger<T> {
		@Override
		public T with(T original, T modifier) {
			return original;
		}
	}

	public static class UIPropertiesMergerBuilder<T extends PropertyDefinition<T>> {
		private final T modifier;
		private final UIPropertiesMerger<T> uiPropertiesMerger;

		public UIPropertiesMergerBuilder(T modifier, UIPropertiesMerger<T> uiPropertiesMerger) {
			this.modifier = modifier;
			this.uiPropertiesMerger = uiPropertiesMerger;
		}

		public   UIPropertiesMerger<T> build(){
			return uiPropertiesMerger;
		}

		public T with(T original) {
			return build().with(original, modifier);
		}
	}
}
