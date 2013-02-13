package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertyType;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import static com.mgs.fantasi.properties.UIPropertyFactory.uiProperty;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.empty;

public class PolygonWireframeTreeBuilder extends BaseWireframeTreeBuilder<PolygonWireframeTreeBuilder> {
	public PolygonWireframeTreeBuilder(PolygonPointsIterator polygonPointsIterator) {
		getUiPropertiesBuilder().withShape(uiProperty(polygonPointsIterator, UIPropertyType.SHAPE));
	}

	@Override
	public WireframeTree build() {
		final Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build());
		return empty(wireframe, getName(), this.getClass());
	}
}
