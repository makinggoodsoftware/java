package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.structures.SizeConstraintsType;
import com.mgs.fantasi.structures.StructureBuilder;
import com.mgs.fantasi.ui.profile.BorderDefinition;

public class Wireframe {
	private final Class<? extends StructureBuilder> structureType;
	private final PolygonPointsIterator shape;
	private final Structure content;
	private final Margin margin;
	private final String name;
	private final BorderDefinition borderDefinition;
	private final SizeConstraintsType sizeConstraintsType;

	public Wireframe(Class<? extends StructureBuilder> structureType, PolygonPointsIterator shape, Structure content, Margin margin, String name, BorderDefinition borderDefinition, SizeConstraintsType sizeConstraintsType) {
		this.structureType = structureType;
		this.shape = shape;
		this.content = content;
		this.margin = margin;
		this.name = name;
		this.borderDefinition = borderDefinition;
		this.sizeConstraintsType = sizeConstraintsType;
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

	public Margin getMargin() {
		return margin;
	}

	public String getName() {
		return name;
	}

	public BorderDefinition getBorder() {
		return borderDefinition;
	}

	public SizeConstraintsType getSizeConstraintsType() {
		return sizeConstraintsType;
	}

	@Override
	public String toString() {
		return "Wireframe{" +
				"borderDefinition=" + borderDefinition +
				", structureType=" + structureType +
				", shape=" + shape +
				", content=" + content +
				", margin=" + margin +
				", name='" + name + '\'' +
				", sizeConstraintsType=" + sizeConstraintsType +
				'}';
	}
}
