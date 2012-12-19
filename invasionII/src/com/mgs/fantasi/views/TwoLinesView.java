package com.mgs.fantasi.views;

import com.mgs.fantasi.properties.measurements.Fraction;
import com.mgs.fantasi.properties.measurements.Fractions;
import com.mgs.fantasi.rendering.structure.grid.CellContent;
import com.mgs.fantasi.rendering.structure.grid.CellContentGenerator;
import com.mgs.fantasi.rendering.wireframe.GridWireframe;
import com.mgs.fantasi.rendering.wireframe.Wireframe;

import static com.mgs.fantasi.rendering.structure.grid.CellContent.withPartialHeight;

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
	public Wireframe<View> buildChildViews() {
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

	@Override
	protected BaseView copySpecifics() {
		return new TwoLinesView(firstLineBuilder.newCopy(), secondLineBuilder.newCopy()).withFirstRowSize(firstLineHeighSizeRatio);
	}

	public View getFirstLineBuilder() {
		return firstLineBuilder;
	}

	public View getSecondLineBuilder() {
		return secondLineBuilder;
	}
}
