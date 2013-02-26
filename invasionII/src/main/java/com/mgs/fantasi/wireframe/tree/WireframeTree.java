package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.Node;
import com.mgs.tree.templates.TemplateTreeWithConnectionInfo;

import java.util.Map;

public class WireframeTree extends TemplateTreeWithConnectionInfo<Wireframe, CollocationInfo, WireframeTree> {
	WireframeTree(WireframeNode root) {
		super(root);
	}

	public WireframeTree(Node<Wireframe> root, Map<CollocationInfo, WireframeTree> children) {
		super(root, children);
	}

	public WireframeLayoutType getType() {
		return getRoot().getType();
	}

	public Wireframe getValue() {
		return getRoot().getValue();
	}

	@Override
	public WireframeNode getRoot() {
		return (WireframeNode) super.getRoot();
	}

}