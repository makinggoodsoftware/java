package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.*;

import static com.mgs.fantasi.structures.Fractions.allWithBase;
import static com.mgs.fantasi.ui.wireframe.CellContent.withPartialHeight;

public class TwoLinesStructureBuilder extends BaseStructureBuilder {

	private final StructureBuilder firstLineBuilder;
	private final StructureBuilder secondLineBuilder;

	private Fraction firstLineHeighSizeRatio = null;

	public TwoLinesStructureBuilder(StructureBuilder firstLineBuilder, StructureBuilder secondLineBuilder) {
		this.firstLineBuilder = firstLineBuilder;
		this.secondLineBuilder = secondLineBuilder;
	}

	public TwoLinesStructureBuilder withFirstRowSize(Fraction firstLineHeighSizeRatio){
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
					return withPartialHeight(firstLineBuilder.build(), firstLineHeighSizeRatio);
				}else{
					Fraction remainder = allWithBase(firstLineHeighSizeRatio.getBase()).minus(firstLineHeighSizeRatio);
					return withPartialHeight(secondLineBuilder.build(), remainder);
				}
			}
		});
		return layout;
	}

	@Override
	protected BaseStructureBuilder copy() {
		return new TwoLinesStructureBuilder(firstLineBuilder.newCopy(), secondLineBuilder.newCopy()).withFirstRowSize(firstLineHeighSizeRatio);
	}

	public StructureBuilder getFirstLineBuilder() {
		return firstLineBuilder;
	}

	public StructureBuilder getSecondLineBuilder() {
		return secondLineBuilder;
	}
}
