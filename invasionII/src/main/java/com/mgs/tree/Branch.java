package com.mgs.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Branch<T, Z> {
	private final Map<Z, Tree<T, Z>> children = new HashMap<Z, Tree<T, Z>>();
	private final ConnectionManager<T, Z> connectionManager;

	public Branch(ConnectionManager<T, Z> connectionManager) {
		this.connectionManager = connectionManager;
	}

	public void addChild(Z linkInfo, Tree<T, Z> child) {
		if (connectionManager.accepts(linkInfo, child)) {
			children.put(linkInfo, child);
		}
	}

	public Set<Map.Entry<Z, Tree<T, Z>>> getLinks() {
		return children.entrySet();
	}

	public ConnectionManager<T, Z> getConnectionManager() {
		return connectionManager;
	}
}