package com.mgs.fantasi.wireframe.tree;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.Node;
import com.mgs.tree.templates.TemplateTreeWithConnectionInfoBuilder;

import java.util.Map;

public class WireframeTreeBuilder extends TemplateTreeWithConnectionInfoBuilder<Wireframe, CollocationInfo, WireframeTree> {
	protected WireframeTreeBuilder(WireframeNode root) {
		super(root);
	}

	@Override
	public WireframeTree create(Node<Wireframe> wireframeNode, Map<CollocationInfo, WireframeTree> children) {
		return new WireframeTree(wireframeNode, children);
	}
}
