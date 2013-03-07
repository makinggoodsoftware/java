package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.Node;
import com.mgs.tree.templates.TemplateTreeWithConnectionInfo;

import java.util.Map;

public class Structure extends TemplateTreeWithConnectionInfo<Wireframe, CollocationInfo, Structure> {
	Structure(WireframeNode root) {
		super(root);
	}

	public Structure(Node<Wireframe> root, Map<CollocationInfo, Structure> children) {
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