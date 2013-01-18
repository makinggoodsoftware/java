package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframe.WireframeTree;

public class PolygonWireframeBuilder extends BaseWireframeBuilder<PolygonWireframeBuilder> {
	public PolygonWireframeBuilder(PolygonPointsIterator polygonPointsIterator) {
		getUiProperties().setShape(polygonPointsIterator);
	}

	@Override
	public WireframeTree build(WireframeContentFactory wireframeContentFactory) {
		return new WireframeTree(new Wireframe(this.getClass(), getName(), getUiProperties()), wireframeContentFactory.empty());
	}
}
