package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.wireframe.GridWireframe;
import com.mgs.fantasi.wireframe.Wireframe;
import com.mgs.fantasi.wireframe.grid.CellContent;
import com.mgs.fantasi.wireframe.grid.CellContentGenerator;

import static com.mgs.fantasi.wireframe.grid.CellContent.withPartialHeight;

public class TwoLinesView extends BaseView {

	private final View firstLineBuilder;
	private final View secondLineBuilder;

	private Fraction firstLineHeighSizeRatio = null;

	public TwoLinesView(View firstLineBuilder, View secondLineBuilder) {
		this.firstLineBuilder = firstLineBuilder;
		this.secondLineBuilder = secondLineBuilder;
	}

	public TwoLinesView withFirstRowSize(Fraction firstLineHeighSizeRatio) {
		this.firstLineHeighSizeRatio = firstLineHeighSizeRatio;
		return this;
	}

	@Override
	public Wireframe buildContent() {
		return
				new GridWireframe().
						withDimension(1, 2).
						withContent(new CellContentGenerator() {
							@Override
							public CellContent generateContentFor(int x, int y) {
								if (y == 0) {
									return withPartialHeight(firstLineBuilder, firstLineHeighSizeRatio);
								} else {
									Fraction remainder = Fractions.allWithBase(firstLineHeighSizeRatio.getBase()).minus(firstLineHeighSizeRatio);
									return withPartialHeight(secondLineBuilder, remainder);
								}
							}
						});

	}
}
