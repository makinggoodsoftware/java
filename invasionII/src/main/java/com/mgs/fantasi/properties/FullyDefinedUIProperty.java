package com.mgs.fantasi.properties;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FullyDefinedUIProperty<Z extends UIPropertyData> implements UIProperty<Z> {
	private final Z data;
	private final PropertyType type;

	public FullyDefinedUIProperty(Z data, PropertyType type) {
		this.data = data;
		if (type == null) throw new RuntimeException("Bullshit!");
		this.type = type;
	}

	@Override
	public boolean isDefined() {
		return true;
	}

	@Override
	public boolean isFullyDefined() {
		return true;
	}

	@Override
	public boolean isNotDefined() {
		return false;
	}

	@Override
	public UIProperty<Z> copy() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public PropertyType getType() {
		return type;
	}

	@Override
	public Z getValue() {
		if (data == null) throw new RuntimeException("WTF!!");
		return data;
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return data != null ? data.hashCode() : 0;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).
				append("data", data).
				toString();
	}
}
