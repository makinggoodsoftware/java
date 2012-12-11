package com.mgs.fantasi.views;

import com.mgs.fantasi.measurements.Fraction;
import com.mgs.fantasi.ui.wireframe.*;

import static com.mgs.fantasi.measurements.Fractions.allWithBase;
import static com.mgs.fantasi.ui.wireframe.CellContent.withPartialHeight;

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
	protected boolean constraintsAreSatisfied() {
		return firstLineHeighSizeRatio != null;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		Grid<Wireframe> layout = GridFactory.withDimensions(1, 2);
		layout.fillCells(new CellContentGenerator<Wireframe>() {
			@Override
			public CellContent<Wireframe> generateContentFor(int x, int y) {
				if (y == 0){
					return withPartialHeight(firstLineBuilder.render(), firstLineHeighSizeRatio);
				}else{
					Fraction remainder = allWithBase(firstLineHeighSizeRatio.getBase()).minus(firstLineHeighSizeRatio);
					return withPartialHeight(secondLineBuilder.render(), remainder);
				}
			}
		});
		return layout;
	}

	@Override
	protected BaseView copy() {
		return new TwoLinesView(firstLineBuilder.newCopy(), secondLineBuilder.newCopy()).withFirstRowSize(firstLineHeighSizeRatio);
	}

	public View getFirstLineBuilder() {
		return firstLineBuilder;
	}

	public View getSecondLineBuilder() {
		return secondLineBuilder;
	}
}