package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Wireframe;

public class RectangleStructureBuilder extends BaseStructureBuilder{
	private StructureBuilder content;

	public RectangleStructureBuilder witchContent(StructureBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	protected Wireframe buildContent() {
		if (content == null) return null;
		return content.build();
	}
}