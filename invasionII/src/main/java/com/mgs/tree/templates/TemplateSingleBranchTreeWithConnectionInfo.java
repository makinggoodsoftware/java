package com.mgs.tree.templates;

import com.mgs.tree.Node;
import com.mgs.tree.TreeWithConnectionInfo;

import java.util.HashMap;
import java.util.Map;

public abstract class TemplateSingleBranchTreeWithConnectionInfo<T, Z, Y extends TreeWithConnectionInfo<T, Z, Y>, W extends Node<T>> implements TreeWithConnectionInfo<T, Z, Y> {
	private final W root;
	private final Map<Z, Y> children;

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

	public TemplateSingleBranchTreeWithConnectionInfo(W root) {
		this(root, new HashMap<Z, Y>());
	}

	public TemplateSingleBranchTreeWithConnectionInfo(W root, Map<Z, Y> children) {
		this.children = children;
		this.root = root;
	}

	@Override
	public final W getRoot() {
		return root;
	}

}
