package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.SimpleStructure;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class RectangleView extends BaseView<RectangleView> {
	private StructureBuilder content;

	private RectangleView(StructureBuilder content) {
		this.content = content;
	}

	private RectangleView() {
	}

	public static RectangleView rectangleWithContent(StructureBuilder content) {
		return new RectangleView(content);
	}

	public static RectangleView emptyRectangle() {
		return new RectangleView();
	}

	public RectangleView withContent(StructureBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	public RectangleView copy() {
		return new RectangleView(content.newCopy());
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		if (content == null) return GridFactory.empty(Wireframe.class);
		return new SimpleStructure<Wireframe>(content.build());
	}

}