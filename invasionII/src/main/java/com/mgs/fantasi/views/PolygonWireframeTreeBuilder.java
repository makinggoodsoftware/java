package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Tree;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

public class PolygonWireframeTreeBuilder extends BaseWireframeTreeBuilder<PolygonWireframeTreeBuilder> {
	public PolygonWireframeTreeBuilder(PolygonPointsIterator polygonPointsIterator) {
		getUiProperties().setShape(polygonPointsIterator);
	}

	@Override
	public Tree<Wireframe> build(WireframeContentFactory wireframeContentFactory) {
		return new Tree<Wireframe>(new Wireframe(this.getClass(), getName(), getUiProperties()), wireframeContentFactory.empty());
	}
}
