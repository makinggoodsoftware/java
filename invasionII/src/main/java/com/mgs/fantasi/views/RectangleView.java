package com.mgs.fantasi.views;

import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

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
	public Wireframe<View> buildLayout(WireframeFactory<View> wireframeFactory) {
		return wireframeFactory.createRectangleWireframe(content);
	}
}