package com.mgs.fantasi.profile;

import org.apache.commons.lang.builder.ToStringBuilder;

public class NotNullProperty<Z extends UIProperty> implements PropertyDefinition<Z>{
    private final Z data;

    public NotNullProperty(Z data) {
        this.data = data;
    }

    @Override
    public PropertyDefinition<Z> merge(PropertyDefinition<Z> original) {
        return this;
    }

    @Override
    public boolean isDefined() {
        return data.isFullyDefined();
    }

    @Override
    public Z getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotNullProperty that = (NotNullProperty) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
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
