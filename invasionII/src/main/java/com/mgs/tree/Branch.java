package com.mgs.tree;

import java.util.HashMap;
import java.util.Map;

public class Branch<T, Z> {
	private final Map<Z, TreeWithConnectionInfo<T, Z>> children = new HashMap<Z, TreeWithConnectionInfo<T, Z>>();
	private final ConnectionManager<T, Z> connectionManager;

	public Branch(ConnectionManager<T, Z> connectionManager) {
		this.connectionManager = connectionManager;
	}

	public void addChild(Z linkInfo, TreeWithConnectionInfo<T, Z> child) {
		if (connectionManager.accepts(linkInfo, child)) {
			children.put(linkInfo, child);
		}
	}

	public ConnectionManager<T, Z> getConnectionManager() {
		return connectionManager;
	}

	public Map<Z, TreeWithConnectionInfo<T, Z>> getChildren() {
		return children;
	}

	public Map<T, Z> getPlainChildren() {
		Map<Z, TreeWithConnectionInfo<T, Z>> childrenInHierarchy = getChildren();
		Map<T, Z> plainChildren = new HashMap<T, Z>();
		for (Map.Entry<Z, TreeWithConnectionInfo<T, Z>> childInHierarchy : childrenInHierarchy.entrySet()) {
			TreeWithConnectionInfo<T, Z> value = childInHierarchy.getValue();
			plainChildren.put(value.getRoot(), childInHierarchy.getKey());
		}
		return plainChildren;
	}
}