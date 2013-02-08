package com.mgs.fantasi.wireframe;

import com.mgs.tree.Branch;
import com.mgs.tree.SingleBranchTreeWithConnectionInfo;
import com.mgs.tree.TreeWithConnectionInfo;

import java.util.HashMap;
import java.util.Map;

public class WireframeContainer {
	private final SingleBranchTreeWithConnectionInfo<Wireframe, CollocationInfo> tree;

	public WireframeContainer(Wireframe wireframe, WireframeCollocationInfoConnectionManager connectionManager) {
		this(new SingleBranchTreeWithConnectionInfo<Wireframe, CollocationInfo>(wireframe, connectionManager));
	}

	public WireframeContainer(Wireframe wireframe, Branch<Wireframe, CollocationInfo> wireframeBranch) {
		this(new SingleBranchTreeWithConnectionInfo<Wireframe, CollocationInfo>(wireframe, wireframeBranch));
	}

	public WireframeContainer(SingleBranchTreeWithConnectionInfo<Wireframe, CollocationInfo> tree) {
		this.tree = tree;
	}

	public Map<CollocationInfo, WireframeContainer> getContent() {
		Map<CollocationInfo, WireframeContainer> childrenWrapped = new HashMap<CollocationInfo, WireframeContainer>();
		for (Map.Entry<CollocationInfo, TreeWithConnectionInfo<Wireframe, CollocationInfo>> child : tree.getChildren().entrySet()) {
			WireframeContainer wireframeContainer = new WireframeContainer((SingleBranchTreeWithConnectionInfo<Wireframe, CollocationInfo>) child.getValue());
			childrenWrapped.put(child.getKey(), wireframeContainer);
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