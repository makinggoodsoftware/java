package com.mgs.fantasi.views;

import com.mgs.fantasi.ui.wireframe.*;

import java.awt.*;

public class VerticalSlicesView extends BaseView {

	private static final int UNDEFINED = -1;
	private final View contentBuilder;
	private int numberOfDivisions = UNDEFINED;

	private VerticalSlicesView(View contentBuilder) {
		this.contentBuilder = contentBuilder;
	}

	public static VerticalSlicesView verticalSlices(View contentBuilder) {
		return new VerticalSlicesView(contentBuilder);
	}

	@Override
	public boolean renderConstraintsAreSatisfied() {
		return numberOfDivisions != UNDEFINED;
	}

	@Override
	public Structure<View> getContent() {
		Grid<View> layout = GridFactory.withDimensions(numberOfDivisions, 1);
		layout.fillCells(new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(contentBuilder);
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
				return new Dimension(numberOfDivisions, 1);
			}

			@Override
			public CellContent<View> getCellContentFor(int x, int y) {
				return CellContent.evenlyDivided(contentBuilder);
			}
		};
	}

	@Override
	public StructureBuilder<View> getChildStructure() {
		GridStructureBuilder<View> grid = StructureType.grid();
		return grid.withDimension(numberOfDivisions, 1).withContent(new CellContentGenerator<View>() {
			@Override
			public CellContent<View> generateContentFor(int x, int y) {
				return CellContent.evenlyDivided(contentBuilder);
			}
		});
	}

	public VerticalSlicesView withVerticalDivisions (int numberOVerticalDivisions){
		this.numberOfDivisions = numberOVerticalDivisions;
		return this;
	}

	@Override
	public VerticalSlicesView copy() {
		return new VerticalSlicesView(contentBuilder.newCopy()).withVerticalDivisions(numberOfDivisions);
	}
}
