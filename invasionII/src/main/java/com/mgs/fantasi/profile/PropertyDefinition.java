package com.mgs.fantasi.profile;

public interface PropertyDefinition<Z extends UIProperty> {
    PropertyDefinition<Z> merge(PropertyDefinition<Z> original);

	boolean isDefined();

    Z getData();
}
