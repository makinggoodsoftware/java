package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.rectangle;

public class SingleChildWireframeTreeBuilder implements WireframeTreeBuilder {
	private final WireframeTreeBuilder content;
	private final String name;
	private final Wireframe wireframe;

	public SingleChildWireframeTreeBuilder(String name, Wireframe wireframe, WireframeTreeBuilder content) {
		this.name = name;
		this.wireframe = wireframe;
		this.content = content;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Wireframe getRootWireframe() {
		return wireframe;
	}

	@Override
	public WireframeTree build() {
		if (content == null) {
			throw new RuntimeException("Content can't be null!!");
		}
		WireframeTree wireframeTree = rectangle(wireframe, getName(), this.getClass());
		wireframeTree.addChild(new CollocationInfo(0, all(), all(), 0, 0), content.build());
		return wireframeTree;
	}
}