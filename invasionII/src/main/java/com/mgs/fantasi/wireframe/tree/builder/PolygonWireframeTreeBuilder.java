package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.UIPropertyType;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import static com.mgs.fantasi.properties.UIPropertyFactory.uiProperty;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.empty;

public class PolygonWireframeTreeBuilder implements WireframeTreeBuilder {
	private final UIPropertiesBuilder uiPropertiesBuilder;
	private final String name;

	public PolygonWireframeTreeBuilder(String name, PolygonPointsIterator polygonPointsIterator, UIPropertiesBuilder uiPropertiesBuilder) {
		this.name = name;
		this.uiPropertiesBuilder = uiPropertiesBuilder;
		uiPropertiesBuilder.withShape(uiProperty(polygonPointsIterator, UIPropertyType.SHAPE));
	}

	@Override
	public UIPropertiesBuilder getUiPropertiesBuilder() {
		return uiPropertiesBuilder;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public WireframeTree build() {
		final Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build());
		return empty(wireframe, getName(), this.getClass());
	}
}
