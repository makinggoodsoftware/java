package com.mgs.tree.templates;

import com.mgs.tree.Node;
import com.mgs.tree.TreeWithConnectionInfo;

import java.util.HashMap;
import java.util.Map;

public abstract class TemplateSingleBranchTreeWithConnectionInfo<T, Z, Y extends TreeWithConnectionInfo<T, Z, Y>> implements TreeWithConnectionInfo<T, Z, Y> {
	private final Node<T> root;
	private final Map<Z, Y> children;

	public TemplateSingleBranchTreeWithConnectionInfo(Node<T> root) {
		this(root, new HashMap<Z, Y>());
	}

	public TemplateSingleBranchTreeWithConnectionInfo(Node<T> root, Map<Z, Y> children) {
		this.children = children;
		this.root = root;
	}

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
			TreeWithConnectionInfo<T, Z, Y> value = childInHierarchy.getValue();
			plainChildren.put(value.getRoot().getValue(), childInHierarchy.getKey());
		}
		return plainChildren;
	}

	@Override
	public Node<T> getRoot() {
		return root;
	}

}
