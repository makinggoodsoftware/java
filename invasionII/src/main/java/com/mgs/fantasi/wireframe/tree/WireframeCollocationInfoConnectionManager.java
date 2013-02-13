package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.ConnectionManager;
import com.mgs.tree.TreeWithConnectionInfo;

public class WireframeCollocationInfoConnectionManager implements ConnectionManager<Wireframe, CollocationInfo> {
	private final WireframeChildrenLayout type;

	WireframeCollocationInfoConnectionManager(WireframeChildrenLayout type) {
		this.type = type;
	}

	@Override
	public boolean accepts(CollocationInfo linkInfo, TreeWithConnectionInfo<Wireframe, CollocationInfo> child) {
		if (type == WireframeChildrenLayout.EMPTY) {
			throw new RuntimeException("Can't accept content for an empty connector!");
		}

		return true;
	}

	public WireframeChildrenLayout getType() {
		return type;
	}
}
