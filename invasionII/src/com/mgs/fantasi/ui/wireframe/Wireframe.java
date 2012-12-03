package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structures.StructureBuilder;
import com.mgs.fantasi.ui.profile.SizeStrategy;

public class Wireframe {
	private final Class<? extends StructureBuilder> structureType;
	private final PolygonPointsIterator shape;
	private final Structure content;
	private final SizeStrategy sizeStrategy;

	public Wireframe(Class<? extends StructureBuilder> structureType, PolygonPointsIterator shape, Structure content, SizeStrategy sizeStrategy) {
		this.structureType = structureType;
		this.shape = shape;
		this.content = content;
		this.sizeStrategy = sizeStrategy;
	}

	public Class<? extends StructureBuilder> getStructureType() {
		return structureType;
	}

	public PolygonPointsIterator getShape() {
		return shape;
	}

	public Structure getContent() {
		return content;
	}

	public SizeStrategy getSizeStrategy() {
		return sizeStrategy;
	}

	@Override
	public String toString() {
		return "Wireframe{" +
				"content=" + content +
				", structureType=" + structureType +
				", shape=" + shape +
				", sizeStrategy=" + sizeStrategy +
				'}';
	}
}
