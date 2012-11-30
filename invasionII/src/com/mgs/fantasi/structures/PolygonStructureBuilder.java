package com.mgs.fantasi.structures;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.Grid;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class PolygonStructureBuilder extends BaseStructureBuilder {

	public PolygonStructureBuilder(PolygonPointsIterator polygonPointsIterator) {
		withShape(polygonPointsIterator);
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	protected Grid<Wireframe> buildLayoutAndChilds() {
		return GridFactory.empty(Wireframe.class);
	}

}
