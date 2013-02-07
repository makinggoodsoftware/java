package com.mgs.fantasi.wireframe.builder;

import com.mgs.fantasi.properties.data.measurements.Fraction;
import com.mgs.fantasi.properties.data.measurements.Fractions;
import com.mgs.fantasi.wireframe.CollocationInfo;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeContainer;
import com.mgs.fantasi.wireframe.WireframeContentFactory;

import java.awt.*;

public class TwoLinesWireframeTreeBuilder extends BaseWireframeTreeBuilder {
	private final WireframeTreeBuilder firstLineTreeBuilder;
	private final WireframeTreeBuilder secondLineTreeBuilder;

	private Fraction firstLineHeightSizeRatio = null;

	public TwoLinesWireframeTreeBuilder(WireframeTreeBuilder firstLineTreeBuilder, WireframeTreeBuilder secondLineTreeBuilder) {
		this.firstLineTreeBuilder = firstLineTreeBuilder;
		this.secondLineTreeBuilder = secondLineTreeBuilder;
	}

	public TwoLinesWireframeTreeBuilder withFirstRowSize(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	@Override
	public WireframeContainer build(final WireframeContentFactory wireframeContentFactory) {
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiPropertiesBuilder().build());
		WireframeContainer wireframeContainer = new WireframeContainer(wireframe, wireframeContentFactory.getGridConnectionManager());

		Dimension dimension = new Dimension(1, 2);
		Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);

		for (int x = 0; x < dimension.width; x++) {
			for (int y = 0; y < dimension.height; y++) {
				CollocationInfo collocationInfo;
				WireframeContainer child;
				if (y == 0) {
					collocationInfo = new CollocationInfo(0, Fractions.all(), firstLineHeightSizeRatio, 0, y);
					child = firstLineTreeBuilder.build(wireframeContentFactory);
				} else {
					collocationInfo = new CollocationInfo(0, Fractions.all(), remainder, 0, y);
					child = secondLineTreeBuilder.build(wireframeContentFactory);
				}
				wireframeContainer.addContent(collocationInfo, child);
			}
		}

		return wireframeContainer;
	}
}
