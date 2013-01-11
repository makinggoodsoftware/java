package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.Placeholder;
import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.WireframeFactory;

import java.awt.*;

public class TwoLinesView extends BaseView {

	private final View firstLineBuilder;
	private final View secondLineBuilder;

	private Fraction firstLineHeightSizeRatio = null;

	public TwoLinesView(View firstLineBuilder, View secondLineBuilder) {
		this.firstLineBuilder = firstLineBuilder;
		this.secondLineBuilder = secondLineBuilder;
	}

	public TwoLinesView withFirstRowSize(Fraction firstLineHeightSizeRatio) {
		this.firstLineHeightSizeRatio = firstLineHeightSizeRatio;
		return this;
	}

	@Override
	public Wireframe<View> buildContent(PlaceholderFactory placeholderFactory) {
		PlaceholderFactory.GridPlaceholderGenerator<View> cellContentGenerator = new PlaceholderFactory.GridPlaceholderGenerator<View>() {
			@Override
			public Placeholder<View> generateContentFor(int x, int y) {
				if (y == 0) {
					return new Placeholder<View>(firstLineBuilder, 0, Fractions.all(), firstLineHeightSizeRatio, 0, y);
				} else {
					Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
					return new Placeholder<View>(secondLineBuilder, 0, Fractions.all(), remainder, 0, y);
				}
			}
		};
		return WireframeFactory.createGridWireframe(cellContentGenerator, new Dimension(1, 2));
	}
}
