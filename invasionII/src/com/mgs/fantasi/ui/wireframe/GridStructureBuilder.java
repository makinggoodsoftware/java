package com.mgs.fantasi.ui.wireframe;

import java.awt.*;

public class GridStructureBuilder<T extends Structurable>  implements StructureBuilder <T>{
	private Dimension dimension;
	private CellContentGenerator<T> cellContentGenerator;

	public GridStructureBuilder<T> withDimension(int x, int y) {
		this.dimension = new Dimension(x, y);
		return this;
	}

	public GridStructureBuilder<T> withContent(CellContentGenerator<T> cellContentGenerator) {
		this.cellContentGenerator = cellContentGenerator;
		return this;
	}

	@Override
	public Structure<T> build() {
		Grid<T> viewGrid = GridFactory.withDimensions(dimension.width, dimension.height);
		viewGrid.fillCells(cellContentGenerator);
		return viewGrid;
	}

	@Override
	public <Z extends Structurable> StructureBuilder<Z> transform(final MyRenderer.StructureBuilderTransformer<T, Z> transformer) {
		return new GridStructureBuilder<Z>().
					withDimension(dimension.width, dimension.height).
					withContent(new CellContentGenerator<Z>() {
						@Override
						public CellContent<Z> generateContentFor(int x, int y) {
							CellContent<T> tCellContent = cellContentGenerator.generateContentFor(x, y);
							Z transform = transformer.transform(tCellContent.getContent());
							return new CellContent<Z>(transform, tCellContent.getHeightSizeRatio(), tCellContent.getWidthSizeRatio());
						}
					});
	}
}
