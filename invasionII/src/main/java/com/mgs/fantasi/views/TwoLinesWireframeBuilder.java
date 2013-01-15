package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.TwoDimensionsIterator;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

import java.awt.*;

public class TwoLinesWireframeBuilder extends BaseWireframeBuilder {
	private final WireframeBuilder firstLineBuilder;
	private final WireframeBuilder secondLineBuilder;

	private Fraction firstLineHeightSizeRatio = null;

	public TwoLinesWireframeBuilder(WireframeBuilder firstLineBuilder, WireframeBuilder secondLineBuilder) {
		this.firstLineBuilder = firstLineBuilder;
		this.secondLineBuilder = secondLineBuilder;
	}

	public TwoLinesWireframeBuilder withFirstRowSize(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	@Override
	public Wireframe build(final WireframeFactory wireframeFactory) {
		TwoDimensionsIterator<Placeholder> cellContentGenerator = new TwoDimensionsIterator<Placeholder>() {
			@Override
			public Placeholder on(int x, int y) {
				if (y == 0) {
					return new Placeholder(firstLineBuilder.build(wireframeFactory), 0, Fractions.all(), firstLineHeightSizeRatio, 0, y);
				} else {
					Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
					return new Placeholder(secondLineBuilder.build(wireframeFactory), 0, Fractions.all(), remainder, 0, y);
				}
			}
		};
		return wireframeFactory.createGridWireframe(cellContentGenerator, new Dimension(1, 2), getUiProperties(), getName(), this.getClass());
	}
}
