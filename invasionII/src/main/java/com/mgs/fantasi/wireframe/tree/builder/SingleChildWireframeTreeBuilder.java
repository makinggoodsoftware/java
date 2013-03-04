package com.mgs.fantasi.wireframe.tree.builder;

import com.mgs.fantasi.properties.UIPropertiesBuilder;
import com.mgs.fantasi.properties.data.polygon.PolygonPointsIterator;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.tree.WireframeTree;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;
import static com.mgs.fantasi.wireframe.tree.WireframeTreeFactory.rectangle;

public class SingleChildWireframeTreeBuilder implements WireframeTreeBuilder {
	private final WireframeTreeBuilder content;
	private final PolygonPointsIterator shape;
	private final UIPropertiesBuilder uiPropertiesBuilder;
	private final String name;

	public SingleChildWireframeTreeBuilder(String name, WireframeTreeBuilder content, PolygonPointsIterator shape, UIPropertiesBuilder uiPropertiesBuilder) {
		this.name = name;
		this.content = content;
		this.shape = shape;
		this.uiPropertiesBuilder = uiPropertiesBuilder;
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
		if (content == null) {
			throw new RuntimeException("Content can't be null!!");
		}
		Wireframe wireframe = new Wireframe(getUiPropertiesBuilder().build(), shape);
		WireframeTree wireframeTree = rectangle(wireframe, getName(), this.getClass());
		wireframeTree.addChild(new CollocationInfo(0, all(), all(), 0, 0), content.build());
		return wireframeTree;
	}
}