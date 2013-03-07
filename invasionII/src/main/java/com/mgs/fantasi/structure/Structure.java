package com.mgs.fantasi.structure;

import com.mgs.fantasi.structure.treeAux.WireframeLayoutType;
import com.mgs.fantasi.structure.treeAux.WireframeNode;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.tree.templates.TemplateTreeWithConnectionInfo;

public class Structure extends TemplateTreeWithConnectionInfo<Wireframe, CollocationInfo, Structure> {
	Structure(WireframeNode root) {
		super(root);
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