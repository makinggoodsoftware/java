package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class RectangleStructureBuilder extends BaseStructureBuilder<RectangleStructureBuilder>{
	private StructureBuilder content;

	private RectangleStructureBuilder(StructureBuilder content) {
		this.content = content;
	}

	private RectangleStructureBuilder() {
	}

	public static RectangleStructureBuilder rectangleWithContent(StructureBuilder content) {
		return new RectangleStructureBuilder(content);
	}

	public static RectangleStructureBuilder emptyRectangle() {
		return new RectangleStructureBuilder();
	}

	public RectangleStructureBuilder withContent(StructureBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		if (content == null) return GridFactory.empty(Wireframe.class);
		return GridFactory.withOnlyOneCell(content.build());
	}

}