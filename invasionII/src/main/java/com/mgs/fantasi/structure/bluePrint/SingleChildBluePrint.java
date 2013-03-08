package com.mgs.fantasi.structure.bluePrint;

import com.mgs.fantasi.structure.CollocationInfo;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.wireframe.Wireframe;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.structure.Structures.simpleStructure;

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
	public Structure buildStructure() {
		if (content == null) {
			throw new RuntimeException("Content can't be null!!");
		}
		Structure structure = simpleStructure(wireframe, getName(), this.getClass());
		structure.addChild(new CollocationInfo(0, all(), all(), 0, 0), content.buildStructure());
		return structure;
	}
}