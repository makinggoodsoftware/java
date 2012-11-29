package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.polygon.PolygonPointsIterator;

public class PolygonStructureBuilder extends BaseStructureBuilder {

	public PolygonStructureBuilder(PolygonPointsIterator polygonPointsIterator) {
		withShape(polygonPointsIterator);
	}

	@Override
	protected Wireframe generateContentFor(int x, int y) {
		throw new RuntimeException("This element can't ever have content");
	}

	@Override
	protected Grid<Wireframe> buildLayout() {
		return GridFactory.empty(Wireframe.class);
	}

}
