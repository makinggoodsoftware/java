package com.mgs.fantasi.wireframe;

import com.mgs.tree.ConnectionManager;
import com.mgs.tree.TemplateSingleBranchTreeWithConnectionInfo;

public class WireframeContainer extends
		TemplateSingleBranchTreeWithConnectionInfo<
				Wireframe, CollocationInfo,
				WireframeContainer, WireframeNode
				> {
	WireframeContainer(WireframeNode root, ConnectionManager<Wireframe, CollocationInfo> connectionManager) {
		super(root, connectionManager);
	}

	public WireframeCollocationInfoConnectionManager getLayoutManager() {
		return (WireframeCollocationInfoConnectionManager) getConnectionManager();
	}

	public WireframeContentType getType() {
		return getLayoutManager().getType();
	}

	public Wireframe getValue() {
		return getRoot().getValue();
	}
}