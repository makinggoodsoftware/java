package com.mgs.tree;

import java.util.Map;

public interface TreeWithConnectionInfo<T, Z> {
	T getRoot();

	Map<Z, ? extends TreeWithConnectionInfo<T, Z>> getChildren();

	void addChild(Z connection, TreeWithConnectionInfo<T, Z> child);

	Map<T, Z> getPlainChildren();
}
