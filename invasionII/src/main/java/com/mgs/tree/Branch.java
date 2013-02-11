package com.mgs.tree;

import java.util.HashMap;
import java.util.Map;

public class Branch<T, Z, Y extends TreeWithConnectionInfo<T, Z>> {
	private final Map<Z, Y> children = new HashMap<Z, Y>();
	private final ConnectionManager<T, Z> connectionManager;

	public Branch(ConnectionManager<T, Z> connectionManager) {
		this.connectionManager = connectionManager;
	}

	public void addChild(Z linkInfo, Y child) {
		if (connectionManager.accepts(linkInfo, child)) {
			children.put(linkInfo, child);
		}
	}

	public ConnectionManager<T, Z> getConnectionManager() {
		return connectionManager;
	}

	public Map<Z, Y> getChildren() {
		return children;
	}

	public Map<T, Z> getPlainChildren() {
		Map<Z, Y> childrenInHierarchy = getChildren();
		Map<T, Z> plainChildren = new HashMap<T, Z>();
		for (Map.Entry<Z, Y> childInHierarchy : childrenInHierarchy.entrySet()) {
			TreeWithConnectionInfo<T, Z> value = childInHierarchy.getValue();
			plainChildren.put(value.getRoot(), childInHierarchy.getKey());
		}
		return plainChildren;
	}
}