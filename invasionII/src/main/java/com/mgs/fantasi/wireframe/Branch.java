package com.mgs.fantasi.wireframe;

import java.util.ArrayList;
import java.util.List;

public class Branch<T> {
	private final List<WireframeChildElement<T>> children = new ArrayList<WireframeChildElement<T>>();
	private final WireframeContentType type;
	private final ConnectionManager<T> connectionManager;

	public Branch(ConnectionManager<T> connectionManager, List<WireframeChildElement<T>> content, WireframeContentType type) {
		this.connectionManager = connectionManager;
		this.type = type;
		addChildren(content);
	}

	public void addChildren(List<WireframeChildElement<T>> content) {
		for (WireframeChildElement<T> toBeAdded : content) {
			addChild(toBeAdded);
		}
	}

	private void addChild(WireframeChildElement<T> toBeAdded) {
		if (this.connectionManager.accepts(toBeAdded)) {
			children.add(toBeAdded);
		}
	}

	public List<WireframeChildElement<T>> getParts() {
		return children;
	}

	public WireframeContentType getType() {
		return type;
	}
}