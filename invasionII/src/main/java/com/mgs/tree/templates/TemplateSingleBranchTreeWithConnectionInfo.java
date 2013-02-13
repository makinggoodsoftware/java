package com.mgs.tree.templates;

import com.mgs.tree.Node;
import com.mgs.tree.TreeWithConnectionInfo;

import java.util.Map;

public abstract class TemplateSingleBranchTreeWithConnectionInfo<T, Z, Y extends TreeWithConnectionInfo<T, Z>, W extends Node<T>> implements TreeWithConnectionInfo<T, Z> {
	private final W root;
	private final Branch<T, Z, Y> branch;

	public TemplateSingleBranchTreeWithConnectionInfo(W root) {
		this(root, new Branch<T, Z, Y>());
	}

	public TemplateSingleBranchTreeWithConnectionInfo(W root, Branch<T, Z, Y> branch) {
		this.branch = branch;
		this.root = root;
	}

	public Branch<T, Z, Y> getChildrenBranch() {
		return branch;
	}

	@Override
	public final W getRoot() {
		return root;
	}

	@Override
	public final Map<Z, Y> getChildren() {
		return getChildrenBranch().getChildren();
	}


	@Override
	public final void addChild(Z connection, TreeWithConnectionInfo<T, Z> child) {
		branch.addChild(connection, (Y) child);
	}

	@Override
	public final Map<T, Z> getPlainChildren() {
		return branch.getPlainChildren();
	}
}
