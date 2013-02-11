package com.mgs.fantasi.wireframe;

import com.mgs.tree.BaseSingleBranchTreeWithConnectionInfo;
import com.mgs.tree.ConnectionManager;

public class WireframeContainer extends BaseSingleBranchTreeWithConnectionInfo<Wireframe, CollocationInfo, WireframeContainer> {

	public WireframeContainer(Wireframe root, ConnectionManager<Wireframe, CollocationInfo> connectionManager) {
		super(root, connectionManager);
	}

	public WireframeCollocationInfoConnectionManager getLayoutManager() {
		return (WireframeCollocationInfoConnectionManager) getConnectionManager();
	}

}