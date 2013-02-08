package com.mgs.tree;

import java.util.Map;

public class SingleBrachTreeWithConnectionInfo<T, Z> implements TreeWithConnectionInfo<T, Z> {
	private final T root;
	private final Branch<T, Z> branch;

	public SingleBrachTreeWithConnectionInfo(T root, ConnectionManager<T, Z> connectionManager) {
		this(root, new Branch<T, Z>(connectionManager));
	}

	public SingleBrachTreeWithConnectionInfo(T root, Branch<T, Z> branch) {
		this.branch = branch;
		this.root = root;
	}

	public Branch<T, Z> getChildrenBranch() {
		return branch;
	}

	@Override
	public T getRoot() {
		return root;
	}

	@Override
	public Map<Z, TreeWithConnectionInfo<T, Z>> getChildren() {
		return getChildrenBranch().getChildren();
	}

	public ConnectionManager<T, Z> getConnectionManager() {
		return branch.getConnectionManager();
	}

	@Override
	public void addChild(Z connection, TreeWithConnectionInfo<T, Z> child) {
		branch.addChild(connection, child);
	}

	@Override
	public Map<T, Z> getPlainChildren() {
		return branch.getPlainChildren();
	}
}
