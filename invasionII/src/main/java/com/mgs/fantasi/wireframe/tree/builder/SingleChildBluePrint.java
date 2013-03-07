package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.Structure;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.rectangle;

public class SingleChildBluePrint implements BluePrint {
	private final BluePrint content;
	private final String name;
	private final Wireframe wireframe;

	public SingleChildBluePrint(String name, Wireframe wireframe, BluePrint content) {
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
	public Structure build() {
		if (content == null) {
			throw new RuntimeException("Content can't be null!!");
		}
		Structure structure = rectangle(wireframe, getName(), this.getClass());
		structure.addChild(new CollocationInfo(0, all(), all(), 0, 0), content.build());
		return structure;
	}
}