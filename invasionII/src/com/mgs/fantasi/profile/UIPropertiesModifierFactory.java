package com.mgs.fantasi.profile;

public class UIPropertiesModifierFactory {

	public static <T extends PropertyDefinition<T>> T merge(T original, T modifier) {
		UIPropertiesMergerBuilder<T> tuiPropertiesMergerBuilder = null;
		if (modifier!=null){
			tuiPropertiesMergerBuilder = new UIPropertiesMergerBuilder<T>(modifier, new UIPropertiesMergerImpl<T>());
		}else{
			tuiPropertiesMergerBuilder = new UIPropertiesMergerBuilder<T>(modifier, new UIPropertiesKeepCurrentValueMerger<T>());
		}
		return tuiPropertiesMergerBuilder.with(original);
	}


	private static class UIPropertiesKeepCurrentValueMerger<T extends PropertyDefinition<T>> implements UIPropertiesMerger<T> {
		@Override
		public T merge(T original, T modifier) {
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

		public T with(T original) {
			return uiPropertiesMerger.merge(original, modifier);
		}
	}
}
