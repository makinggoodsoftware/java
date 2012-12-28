package com.mgs.fantasi.properties;

public interface UIPropertyProvider<Z extends UIProperty> {
    UIPropertyProvider<Z> merge(UIPropertyProvider<Z> original);

	boolean isDefined();

    Z getData();
}
