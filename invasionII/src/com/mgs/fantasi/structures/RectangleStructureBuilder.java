package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class RectangleStructureBuilder extends BaseStructureBuilder{
	private StructureBuilder content;

	public RectangleStructureBuilder witchContent(StructureBuilder content) {
		this.content = content;
		return this;
	}

	@Override
	protected Wireframe generateContentFor(int x, int y) {
		return content.build();
	}

	@Override
	protected Grid<Wireframe> buildLayout() {
		if (content == null) return GridFactory.empty(Wireframe.class);
		return GridFactory.withOnlyOneCell(content.build());
	}
}