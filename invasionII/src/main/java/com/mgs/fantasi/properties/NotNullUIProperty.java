package com.mgs.fantasi.properties;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class NotNullUIProperty<Z extends UIProperty> implements UIPropertyProvider<Z> {
	private final Z data;

	public NotNullUIProperty(Z data) {
		this.data = data;
	}

	@Override
	public UIPropertyProvider<Z> filterStronger(UIPropertyProvider<Z> toApply) {
		return this;
	}

	@Override
	public boolean isDefined() {
		return data.isFullyDefined();
	}

	@Override
	public Z getValue() {
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
