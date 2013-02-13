package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.ConnectionManager;
import com.mgs.tree.TemplateSingleBranchTreeWithConnectionInfo;

public class WireframeTree extends
		TemplateSingleBranchTreeWithConnectionInfo<
				Wireframe, CollocationInfo,
				WireframeTree, WireframeNode
				> {
	WireframeTree(WireframeNode root, ConnectionManager<Wireframe, CollocationInfo> connectionManager) {
		super(root, connectionManager);
	}

	public WireframeCollocationInfoConnectionManager getLayoutManager() {
		return (WireframeCollocationInfoConnectionManager) getConnectionManager();
	}

	public WireframeChildrenLayout getType() {
		return getLayoutManager().getType();
	}

	public Wireframe getValue() {
		return getRoot().getValue();
	}
}