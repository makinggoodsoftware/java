package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structures.StructureBuilder;
import com.mgs.fantasi.ui.profile.SizeStrategy;

public class Wireframe {
	private final Class<? extends StructureBuilder> structureType;
	private final PolygonPointsIterator shape;
	private final Structure content;
	private final SizeStrategy sizeStrategy;
	private final Margin margin;
	private String name;

	public Wireframe(Class<? extends StructureBuilder> structureType, PolygonPointsIterator shape, Structure content, SizeStrategy sizeStrategy, Margin margin, String name) {
		this.structureType = structureType;
		this.shape = shape;
		this.content = content;
		this.sizeStrategy = sizeStrategy;
		this.margin = margin;
		this.name = name;
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

	public Margin getMargin() {
		return margin;
	}

	@Override
	public String toString() {
		return "Wireframe{" +
				"content=" + content +
				", structureType=" + structureType +
				", shape=" + shape +
				", sizeStrategy=" + sizeStrategy +
				", margin=" + margin +
				", name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}
}
