package com.mgs.tree;

import java.util.Map;
import java.util.Set;

public class Tree<T, Z> {
	private final T root;
	private final Branch<T, Z> branch;

	public Tree(T root, ConnectionManager<T, Z> connectionManager) {
		this(root, new Branch<T, Z>(connectionManager));
	}

	public Tree(T root, Branch<T, Z> branch) {
		this.branch = branch;
		this.root = root;
	}

	public Branch<T, Z> getChildrenBranch() {
		return branch;
	}

	public T getRoot() {
		return root;
	}

	public Set<Map.Entry<Z, Tree<T, Z>>> getChildren() {
		return getChildrenBranch().getLinks();
	}

	public ConnectionManager<T, Z> getConnectionManager() {
		return branch.getConnectionManager();
	}

	public void addChild(Z connection, Tree<T, Z> child) {
		branch.addChild(connection, child);
	}
}
