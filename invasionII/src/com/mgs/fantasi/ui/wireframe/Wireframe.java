package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.views.View;

public class Wireframe {
	private final Class<? extends View> structureType;
	private final PolygonPointsIterator shape;
	private final Structure content;
	private final Margin margin;
	private String name;

	public Wireframe(Class<? extends View> structureType, PolygonPointsIterator shape, Structure content, Margin margin, String name) {
		this.structureType = structureType;
		this.shape = shape;
		this.content = content;
		this.margin = margin;
		this.name = name;
	}

	public Class<? extends View> getStructureType() {
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

	@Override
	public String toString() {
		return "Wireframe{" +
				"content=" + content +
				", structureType=" + structureType +
				", shape=" + shape +
				", margin=" + margin +
				", name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}
}
