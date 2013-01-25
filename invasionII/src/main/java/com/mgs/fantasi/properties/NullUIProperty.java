package com.mgs.fantasi.properties;

import java.util.HashMap;
import java.util.Map;

public class NullUIProperty<Z extends UIProperty> implements UIPropertyProvider<Z> {
	private final static Map<Class<? extends UIProperty>, NullUIProperty<? extends UIProperty>> nullProperties = new HashMap<Class<? extends UIProperty>, NullUIProperty<? extends UIProperty>>();
	private final Class<Z> ofType;

	private NullUIProperty(Class<Z> ofType) {
		this.ofType = ofType;
	}

	@SuppressWarnings("unchecked")
	public static <Z extends UIProperty> NullUIProperty<Z> nullProperty(Class<Z> ofType) {
		if (nullProperties.containsKey(ofType)) {
			return (NullUIProperty<Z>) nullProperties.get(ofType);
		} else {
			NullUIProperty<Z> newNullProperty = new NullUIProperty<Z>(ofType);
			nullProperties.put(ofType, newNullProperty);
			return newNullProperty;
		}
	}

	@Override
	public UIPropertyProvider<Z> filterStronger(UIPropertyProvider<Z> toApply) {
		return toApply;
	}

	@Override
	public boolean isDefined() {
		return false;
	}

	@Override
	public Z getValue() {
		throw new UnsupportedOperationException("Can't get the data for a null property, this will produce a Null, you should check for is defined");
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (!(o instanceof NullUIProperty)) return false;

		NullUIProperty that = (NullUIProperty) o;

		return !(ofType != null ? !ofType.equals(that.ofType) : that.ofType != null);

	}

	@Override
	public int hashCode() {
		return ofType != null ? ofType.hashCode() : 0;
	}
}
