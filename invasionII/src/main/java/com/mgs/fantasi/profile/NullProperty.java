package com.mgs.fantasi.profile;

import java.util.HashMap;
import java.util.Map;

public class NullProperty<Z extends UIProperty> implements PropertyDefinition<Z>{
	private final static Map<Class<? extends UIProperty>, NullProperty<? extends UIProperty>> nullProperties = new HashMap<Class<? extends UIProperty>, NullProperty<? extends UIProperty>>();
	private final Class<Z> ofType;

	private NullProperty(Class<Z> ofType) {
		this.ofType = ofType;
	}

	public static <Z extends UIProperty> NullProperty<Z> nullProperty(Class<Z> ofType){
		if (nullProperties.containsKey(ofType)) {
			return (NullProperty<Z>) nullProperties.get(ofType);
		}else{
			NullProperty<Z> newNullProperty = new NullProperty<Z>(ofType);
			nullProperties.put(ofType, newNullProperty);
			return newNullProperty;
		}
	}

	@Override
    public PropertyDefinition<Z> merge(PropertyDefinition<Z> original) {
        return original;
    }

    @Override
    public boolean isDefined() {
        return false;
    }

    @Override
    public Z getData() {
        throw new UnsupportedOperationException("Can't get the data for a null property, this will produce a Null, you should check for is defined");
    }

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (!(o instanceof NullProperty)) return false;

		NullProperty that = (NullProperty) o;

		if (ofType != null ? !ofType.equals(that.ofType) : that.ofType != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return ofType != null ? ofType.hashCode() : 0;
	}
}
