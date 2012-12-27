package com.mgs.fantasi.profile;

public abstract class PropertyDefinitionBase<T extends PropertyDefinition> implements PropertyDefinition<T> {
	@Override
	public T merge(T original) {
		if (isDefined()){
			return copy();
		}else{
			return original;
		}
	}

	@Override
	public boolean isDefined() {
		return true;
	}

	public static class NullPropertyDefinition<T extends PropertyDefinition> extends PropertyDefinitionBase<T> {
		@Override
		public final boolean isDefined() {
			return false;
		}

		@Override
		public T copy() {
			throw new RuntimeException("Can't copy a Null property definition");
		}
	}
}
