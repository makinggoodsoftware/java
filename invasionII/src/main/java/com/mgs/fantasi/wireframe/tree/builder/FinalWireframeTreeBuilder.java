package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.emptyTree;

public class FinalWireframeTreeBuilder implements WireframeTreeBuilder {
	private final String name;
	private final PolygonPointsIterator shape;
	private final UIPropertiesBuilder uiPropertiesBuilder;

	public FinalWireframeTreeBuilder(String name, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		this.name = name;
		this.shape = shape;
		this.uiPropertiesBuilder = uiPropertiesBuilder;
	}

	@Override
	public WireframeTree build() {
		Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build(), shape);
		return emptyTree(wireframe, getName(), this.getClass());
	}

	@Override
	public UIPropertiesBuilder getUiPropertiesBuilder() {
		return uiPropertiesBuilder;
	}

	@Override
	public String getName() {
		return name;
	}
}
