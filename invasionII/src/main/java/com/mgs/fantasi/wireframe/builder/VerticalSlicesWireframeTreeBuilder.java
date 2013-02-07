package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContainer;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

import java.awt.*;

import static com.mgs.fantasi.properties.data.measurements.Fractions.all;

public class VerticalSlicesWireframeTreeBuilder extends BaseWireframeTreeBuilder {

	private static final int UNDEFINED = -1;
	private final WireframeTreeBuilder contentTreeBuilder;
	private int numberOfDivisions = UNDEFINED;

	public VerticalSlicesWireframeTreeBuilder(WireframeTreeBuilder contentTreeBuilder) {
		this.contentTreeBuilder = contentTreeBuilder;
	}

	@Override
	public WireframeContainer build(final WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiPropertiesBuilder().build());
		WireframeContainer wireframeContainer = new WireframeContainer(wireframe, wireframeContentFactory.getGridConnectionManager());
		Dimension dimension = new Dimension(numberOfDivisions, 1);
		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				CollocationInfo collocationInfo = new CollocationInfo(0, all(), all(), x, y);
				WireframeContainer child = contentTreeBuilder.build(wireframeContentFactory);
				wireframeContainer.addContent(collocationInfo, child);
			}
		}
		return wireframeContainer;
	}

	public VerticalSlicesWireframeTreeBuilder withVerticalDivisions(int numberOVerticalDivisions) {
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}
}
