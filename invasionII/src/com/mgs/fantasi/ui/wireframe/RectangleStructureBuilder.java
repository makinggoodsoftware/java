package com.mgs.fantasi.ui.wireframe;

import com.mgs.fantasi.views.View;

public class RectangleStructureBuilder implements StructureBuilder{
	private View content;

	public RectangleStructureBuilder withContent(View content) {
		this.content = content;
		return this;
	}

	@Override
	public ReadyForRendering produce() {
		return new ReadyForRendering(this);
	}
}
