package com.mgs.fantasi.structures;

import com.mgs.fantasi.ui.wireframe.*;

public class PijamaRowsStructureBuilder extends BaseStructureBuilder {
	private static final int UNDEFINED = -1;

	private final TwoLinesStructureBuilder generationBuilder;

	private int numberOfGenerations = UNDEFINED;

	private PijamaRowsStructureBuilder(StructureBuilder firstRowBuilder, StructureBuilder secondRowBuilder) {
		generationBuilder = new TwoLinesStructureBuilder(firstRowBuilder, secondRowBuilder);
	}

	public static PijamaRowsStructureBuilder pijamaRows(StructureBuilder firstRowBuilder, StructureBuilder secondRowBuilder) {
		return new PijamaRowsStructureBuilder(firstRowBuilder, secondRowBuilder);
	}

	public PijamaRowsStructureBuilder withFirstRowSize(Fraction sizeContraints){
		generationBuilder.withFirstRowSize(sizeContraints);
		return this;
	}

	public PijamaRowsStructureBuilder withNumberOfGerations (int numberOfGerations){
		numberOfGenerations = numberOfGerations;
		return this;
	}

	@Override
	protected boolean constraintsAreSatisfied() {
		if (! generationBuilder.constraintsAreSatisfied()) return false;
		return numberOfGenerations != UNDEFINED;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		Grid<Wireframe> layout = GridFactory.withDimensions(1, numberOfGenerations);
		layout.fillCells(new CellContentGenerator<Wireframe>() {
			@Override
			public CellContent<Wireframe> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(generationBuilder.build());
			}
		});
		return layout;
	}

	@Override
	protected BaseStructureBuilder copy() {
		StructureBuilder firstLineBuilder = generationBuilder.getFirstLineBuilder();
		StructureBuilder secondLineBuilder = generationBuilder.getSecondLineBuilder();
		return new PijamaRowsStructureBuilder(firstLineBuilder.newCopy(), secondLineBuilder).withNumberOfGerations(numberOfGenerations);
	}

}
