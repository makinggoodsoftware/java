package com.mgs.tree;

public interface ConnectionManager<T, Z> {
	boolean accepts(Z linkInfo, Tree<T, Z> child);
}
