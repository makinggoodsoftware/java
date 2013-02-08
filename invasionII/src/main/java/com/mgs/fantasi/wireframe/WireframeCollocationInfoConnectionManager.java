package com.mgs.fantasi.wireframe;

import com.mgs.tree.ConnectionManager;
import com.mgs.tree.TreeWithConnectionInfo;

public class WireframeCollocationInfoConnectionManager implements ConnectionManager<Wireframe, CollocationInfo> {
	private final WireframeContentType type;

	WireframeCollocationInfoConnectionManager(WireframeContentType type) {
		this.type = type;
	}

	@Override
	public boolean accepts(CollocationInfo linkInfo, TreeWithConnectionInfo<Wireframe, CollocationInfo> child) {
		if (type == WireframeContentType.EMPTY) {
			throw new RuntimeException("Can't accept content for an empty connector!");
		}

		return true;
	}

	public WireframeContentType getType() {
		return type;
	}
}
