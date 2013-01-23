package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.*;

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
	public Tree<Wireframe, CollocationInfo> build(final WireframeContentFactory wireframeContentFactory) {
		TwoDimensionsIterator<WireframeChildElement<Wireframe, CollocationInfo>> cellContentGenerator = new TwoDimensionsIterator<WireframeChildElement<Wireframe, CollocationInfo>>() {
			@Override
			public WireframeChildElement<Wireframe, CollocationInfo> on(int x, int y) {
				if (y == 0) {
					return new WireframeChildElement<Wireframe, CollocationInfo>(firstLineTreeBuilder.build(wireframeContentFactory), 0, Fractions.all(), firstLineHeightSizeRatio, 0, y);
				} else {
					Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
					return new WireframeChildElement<Wireframe, CollocationInfo>(secondLineTreeBuilder.build(wireframeContentFactory), 0, Fractions.all(), remainder, 0, y);
				}
			}
		};
		Wireframe wireframe = new Wireframe(this.getClass(), getName(), getUiProperties());
		return new Tree<Wireframe, CollocationInfo>(wireframe, wireframeContentFactory.grid(cellContentGenerator, new Dimension(1, 2)));
	}
}
