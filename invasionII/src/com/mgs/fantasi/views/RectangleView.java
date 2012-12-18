package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.*;

public class RectangleView extends BaseView<RectangleView> {
	private View content;

	private RectangleView(View content) {
		this.content = content;
	}

	private RectangleView() {
	}

	public static RectangleView emptyRectangle() {
		return new RectangleView();
	}

	public RectangleView withContent(View content) {
		this.content = content;
		return this;
	}

	@Override
	public RectangleView copy() {
		return new RectangleView(content.newCopy());
	}

	@Override
	public StructureBuilder getChildStructure() {
		RectangleStructureBuilder emptyRectangle = StructureType.rectangle();
		if (content==null) return emptyRectangle;
		return emptyRectangle.withContent(content);
	}
}