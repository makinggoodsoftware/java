package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.properties.UIPropertyType;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContainer;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

import static com.mgs.fantasi.properties.UIPropertyFactory.uiProperty;

public class PolygonWireframeTreeBuilder extends BaseWireframeTreeBuilder<PolygonWireframeTreeBuilder> {
	public PolygonWireframeTreeBuilder(PolygonPointsIterator polygonPointsIterator) {
		getUiPropertiesBuilder().withShape(uiProperty(polygonPointsIterator, UIPropertyType.SHAPE));
	}

	@Override
	public WireframeContainer build(WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiPropertiesBuilder().build());

		return new WireframeContainer(wireframe, wireframeContentFactory.getEmptyConnectionManager());
	}
}
