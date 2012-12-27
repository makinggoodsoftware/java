package com.mgs.fantasi.profile;

public abstract class PropertyDefinitionBase<T extends PropertyDefinition, Z extends PropertyDefinitionBean> implements PropertyDefinition<T> {
    private final Z data;

    protected PropertyDefinitionBase(Z data) {
        this.data = data;
    }

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

	public static class NullPropertyDefinition<T extends PropertyDefinition> extends PropertyDefinitionBase<T, NullData> {
        private NullPropertyDefinition() {
            super(new NullData());
        }

        public static <T extends PropertyDefinition> NullPropertyDefinition<T> nullPropertyDefinition (){
            return new NullPropertyDefinition<T>();
        }

        @Override
		public final boolean isDefined() {
			return false;
		}

		@Override
		public T copy() {
			throw new RuntimeException("Can't copy a Null property definition");
		}
	}

    private static class NullData extends PropertyDefinitionBean {
    }
}
