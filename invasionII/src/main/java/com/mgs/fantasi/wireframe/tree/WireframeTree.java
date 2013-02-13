package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.templates.TemplateSingleBranchTreeWithConnectionInfo;

public class WireframeTree extends
		TemplateSingleBranchTreeWithConnectionInfo<
				Wireframe, CollocationInfo,
				WireframeTree, WireframeNode
				> {
	WireframeTree(WireframeNode root) {
		super(root);
	}

	public WireframeLayoutType getType() {
		return getRoot().getType();
	}

	public Wireframe getValue() {
		return getRoot().getValue();
	}
}