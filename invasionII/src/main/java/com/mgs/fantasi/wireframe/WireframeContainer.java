package com.mgs.fantasi.wireframe;

import com.mgs.tree.Branch;
import com.mgs.tree.Tree;

import java.util.HashMap;
import java.util.Map;

public class WireframeContainer {
	private final Tree<Wireframe, CollocationInfo> tree;

	public WireframeContainer(Wireframe wireframe, WireframeCollocationInfoConnectionManager connectionManager) {
		this(new Tree<Wireframe, CollocationInfo>(wireframe, connectionManager));
	}

	public WireframeContainer(Wireframe wireframe, Branch<Wireframe, CollocationInfo> wireframeBranch) {
		this(new Tree<Wireframe, CollocationInfo>(wireframe, wireframeBranch));
	}

	public WireframeContainer(Tree<Wireframe, CollocationInfo> tree) {
		this.tree = tree;
	}

	public Map<CollocationInfo, WireframeContainer> getContent() {
		Map<CollocationInfo, WireframeContainer> childrenWrapped = new HashMap<CollocationInfo, WireframeContainer>();
		for (Map.Entry<CollocationInfo, Tree<Wireframe, CollocationInfo>> child : tree.getChildren()) {
			childrenWrapped.put(child.getKey(), new WireframeContainer(child.getValue()));
		}
		return childrenWrapped;
	}

	public WireframeCollocationInfoConnectionManager getLayoutManager() {
		return (WireframeCollocationInfoConnectionManager) tree.getConnectionManager();
	}

	public void addContent(CollocationInfo collocationInfo, WireframeContainer wireframe) {
		tree.addChild(collocationInfo, wireframe.tree);
	}

	public Wireframe getWireframe() {
		return tree.getRoot();
	}
}