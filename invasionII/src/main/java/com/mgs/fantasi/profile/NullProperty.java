package com.mgs.fantasi.profile;

public class NullProperty<Z extends PropertyDefinitionBean> implements PropertyDefinition<Z>{

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
    public boolean equals(Object that) {
        if (that == null) return false;
        return that instanceof NullProperty;
    }
}
