package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.*;

public class PolygonWireframeTreeBuilder extends BaseWireframeTreeBuilder<PolygonWireframeTreeBuilder> {
	public PolygonWireframeTreeBuilder(PolygonPointsIterator polygonPointsIterator) {
		getUiProperties().setShape(polygonPointsIterator);
	}

	@Override
	public Tree<Wireframe, CollocationInfo> build(WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		Branch<Wireframe, CollocationInfo> wireframeCollocationInfoBranch = new Branch<Wireframe, CollocationInfo>(wireframeContentFactory.getEmptyConnectionManager());

		return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeCollocationInfoBranch);
	}
}
