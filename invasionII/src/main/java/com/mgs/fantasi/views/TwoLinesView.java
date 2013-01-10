package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.PlaceholderFactory;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellContentGenerator;

import java.awt.*;

import static com.mgs.fantasi.wireframe.grid.CellContent.withPartialHeight;

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
		CellContentGenerator<View> cellContentGenerator = new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				if (y == 0) {
					return withPartialHeight(firstLineBuilder, firstLineHeightSizeRatio);
				} else {
					Fraction remainder = Fractions.allWithBase(firstLineHeightSizeRatio.getBase()).minus(firstLineHeightSizeRatio);
					return withPartialHeight(secondLineBuilder, remainder);
				}
			}
		};
		return
				new GridWireframe<View>(placeholderFactory.gridPlaceholders(cellContentGenerator, new Dimension(1, 2))).
						withDimension(1, 2).
						withContent(cellContentGenerator);

	}
}
