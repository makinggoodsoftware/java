package com.mgs.fantasi.structures;

import com.mgs.fantasi.polygon.PolygonPointsIterator;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class PolygonStructureBuilder extends BaseStructureBuilder {

	private PolygonStructureBuilder(PolygonPointsIterator polygonPointsIterator) {
		withShape(polygonPointsIterator);
	}

	public static PolygonStructureBuilder polygon(PolygonPointsIterator polygonPointsIterator) {
		return new PolygonStructureBuilder(polygonPointsIterator);
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		return true;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		return GridFactory.empty(Wireframe.class);
	}

}
