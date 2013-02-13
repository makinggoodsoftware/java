package com.mgs.tree.templates;

import com.mgs.tree.TreeWithConnectionInfo;

public interface ConnectionManager<T, Z> {
	boolean accepts(Z linkInfo, TreeWithConnectionInfo<T, Z> child);
}
