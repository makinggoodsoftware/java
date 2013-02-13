package com.mgs.tree;

import java.util.Map;

public abstract class TemplateSingleBranchTreeWithConnectionInfo<T, Z, Y extends TreeWithConnectionInfo<T, Z>, W extends Node<T>> implements TreeWithConnectionInfo<T, Z> {
	private final W root;
	private final Branch<T, Z, Y> branch;

	public TemplateSingleBranchTreeWithConnectionInfo(W root, ConnectionManager<T, Z> connectionManager) {
		this(root, new Branch<T, Z, Y>(connectionManager));
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

	public ConnectionManager<T, Z> getConnectionManager() {
		return branch.getConnectionManager();
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
