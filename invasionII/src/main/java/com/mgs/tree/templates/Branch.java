package com.mgs.tree.templates;

import com.mgs.tree.TreeWithConnectionInfo;

import java.util.HashMap;
import java.util.Map;

public class Branch<T, Z, Y extends TreeWithConnectionInfo<T, Z>> {
	private final Map<Z, Y> children = new HashMap<Z, Y>();

	public void addChild(Z linkInfo, Y child) {
		children.put(linkInfo, child);
	}

	public Map<Z, Y> getChildren() {
		return children;
	}

	public Map<T, Z> getPlainChildren() {
		Map<Z, Y> childrenInHierarchy = getChildren();
		Map<T, Z> plainChildren = new HashMap<T, Z>();
		for (Map.Entry<Z, Y> childInHierarchy : childrenInHierarchy.entrySet()) {
			Y value = childInHierarchy.getValue();
			plainChildren.put(value.getRoot().getValue(), childInHierarchy.getKey());
		}
		return plainChildren;
	}
}