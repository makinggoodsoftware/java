package com.mgs.fantasi.views;

import com.mgs.fantasi.measurements.Fraction;
import com.mgs.fantasi.ui.wireframe.*;

import java.awt.*;

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
	public boolean renderConstraintsAreSatisfied() {
		return firstLineHeighSizeRatio != null;
	}

	@Override
	public Structure<View> getContent() {
		Grid<View> layout = GridFactory.withDimensions(1, 2);
		layout.fillCells(new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				if (y == 0){
					return withPartialHeight(firstLineBuilder, firstLineHeighSizeRatio);
				}else{
					Fraction remainder = allWithBase(firstLineHeighSizeRatio.getBase()).minus(firstLineHeighSizeRatio);
					return withPartialHeight(secondLineBuilder, remainder);
				}
			}
		});
		return layout;
	}

	@Override
	public StructureFactory.StructureType getContentStructureType() {
		return null;
	}

	@Override
	public ContentStructureStrategy getContentStructureStrategy() {
		return new GridContentStructureStrategy() {
			@Override
			public Dimension getDimension() {
				return new Dimension(1, 2);
			}

			@Override
			public CellContent<View> getCellContentFor(int x, int y) {
				if (y == 0){
					return withPartialHeight(firstLineBuilder, firstLineHeighSizeRatio);
				}else{
					Fraction remainder = allWithBase(firstLineHeighSizeRatio.getBase()).minus(firstLineHeighSizeRatio);
					return withPartialHeight(secondLineBuilder, remainder);
				}
			}
		};
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
