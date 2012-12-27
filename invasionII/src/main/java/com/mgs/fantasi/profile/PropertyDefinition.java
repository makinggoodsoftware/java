package com.mgs.fantasi.profile;

public interface PropertyDefinition<Z extends PropertyDefinitionBean> {
    PropertyDefinition<Z> merge(PropertyDefinition<Z> original);

	boolean isDefined();

    Z getData();
}
