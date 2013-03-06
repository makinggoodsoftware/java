package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.emptyTree;

public class FinalWireframeTreeBuilder implements WireframeTreeBuilder {
	private final String name;
	private final Wireframe wireframe;

	public FinalWireframeTreeBuilder(String name, Wireframe wireframe) {
		this.name = name;
		this.wireframe = wireframe;
	}

	@Override
	public WireframeTree build() {
		return emptyTree(wireframe, getName(), this.getClass());
	}

	@Override
	public Wireframe getRootWireframe() {
		return wireframe;
	}

	@Override
	public String getName() {
		return name;
	}
}
