package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.polygon.PolygonPointsIterator;

public class PolygonStructureBuilder extends BaseStructureBuilder {

	public PolygonStructureBuilder(PolygonPointsIterator polygonPointsIterator) {
		withShape(polygonPointsIterator);
	}

	@Override
	protected Wireframe buildContent() {
		return null;
	}

}
