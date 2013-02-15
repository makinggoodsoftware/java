package com.mgs.tree;

public interface TreeWithConnectionInfoBuilder
		<T, Z, Y extends TreeWithConnectionInfo<T, Z, Y>> {
	public TreeWithConnectionInfoBuilder<T, Z, Y> withChild(TreeWithConnectionInfoBuilder<T, Z, Y> value, Z connectionInfo);

	public TreeWithConnectionInfo<T, Z, Y> build();
}
