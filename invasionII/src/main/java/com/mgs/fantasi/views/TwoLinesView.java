package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.rendering.wireframe.grid.CellContent;
import com.mgs.fantasi.rendering.wireframe.grid.CellContentGenerator;
import com.mgs.fantasi.rendering.wireframe.GridWireframe;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

import static com.mgs.fantasi.rendering.wireframe.grid.CellContent.withPartialHeight;

public class TwoLinesView extends BaseView {

	private final View firstLineBuilder;
	private final View secondLineBuilder;

	private Fraction firstLineHeighSizeRatio = null;

	public TwoLinesView(View firstLineBuilder, View secondLineBuilder) {
		this.firstLineBuilder = firstLineBuilder;
		this.secondLineBuilder = secondLineBuilder;
	}

	public TwoLinesView withFirstRowSize(Fraction firstLineHeighSizeRatio){
		this.firstLineHeighSizeRatio = firstLineHeighSizeRatio;
		return this;
	}

	@Override
	public Wireframe<View> buildContent() {
		return
			new GridWireframe<View>().
				withDimension(1, 2).
				withContent(new CellContentGenerator<View>() {
					@Override
					public CellContent<View> generateContentFor(int x, int y) {
						if (y == 0){
							return withPartialHeight(firstLineBuilder, firstLineHeighSizeRatio);
						}else{
							Fraction remainder = Fractions.allWithBase(firstLineHeighSizeRatio.getBase()).minus(firstLineHeighSizeRatio);
							return withPartialHeight(secondLineBuilder, remainder);
						}
					}
				});

	}
}
