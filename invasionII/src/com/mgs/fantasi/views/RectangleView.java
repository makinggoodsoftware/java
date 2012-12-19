package com.mgs.fantasi.views;

import com.mgs.fantasi.rendering.wireframe.RectangleWireframe;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

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
	public RectangleView copySpecifics() {
		return new RectangleView(content.newCopy());
	}

	@Override
	public Wireframe buildChildViews() {
		RectangleWireframe emptyRectangle = new RectangleWireframe();
		if (content==null) return emptyRectangle;
		return emptyRectangle.withContent(content);
	}
}