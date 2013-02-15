package com.mgs.tree;


import java.util.Map;

public interface TreeWithConnectionInfo<T, Z, Y extends TreeWithConnectionInfo<T, Z, Y>> {
	Node<T> getRoot();

	Map<Z, Y> getChildren();

	void addChild(Z connection, Y child);

	Map<T, Z> getPlainChildren();

}
