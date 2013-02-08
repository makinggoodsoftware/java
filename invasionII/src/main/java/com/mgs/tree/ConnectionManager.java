package com.mgs.tree;

public interface ConnectionManager<T, Z> {
	boolean accepts(Z linkInfo, TreeWithConnectionInfo<T, Z> child);
}
