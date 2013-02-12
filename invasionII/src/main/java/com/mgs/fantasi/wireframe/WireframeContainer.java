package com.mgs.fantasi.wireframe;

import com.mgs.tree.BaseSingleBranchTreeWithConnectionInfo;
import com.mgs.tree.ConnectionManager;
import com.mgs.tree.Node;

public class WireframeContainer extends BaseSingleBranchTreeWithConnectionInfo<Wireframe, CollocationInfo, WireframeContainer> {

	WireframeContainer(Wireframe root, ConnectionManager<Wireframe, CollocationInfo> connectionManager) {
		super(new Node<Wireframe>(root), connectionManager);
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