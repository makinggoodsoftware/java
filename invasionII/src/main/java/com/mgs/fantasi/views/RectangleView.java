package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.RectangleWireframe;
import com.mgs.fantasi.wireframe.Wireframe;

public class RectangleView extends BaseView<RectangleView> {
	private View content;

	private RectangleView() {
	}

	public static RectangleView rectangle() {
		return new RectangleView();
	}

	public RectangleView withContent(View content) {
		this.content = content;
		return this;
	}

	@Override
	public Wireframe<View> buildContent(PlaceholderFactory placeholderFactory) {
		RectangleWireframe<View> emptyRectangle = new RectangleWireframe<View>(placeholderFactory.rectanglePlaceholder(content));
		if (content == null) return emptyRectangle;
		return emptyRectangle.withContent(content);
	}
}