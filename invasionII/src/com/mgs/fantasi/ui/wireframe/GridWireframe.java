package com.mgs.fantasi.ui.wireframe;

import java.awt.*;

public class GridWireframe<T extends Structurable>  implements Wireframe<T> {
	private Dimension dimension;
	private CellContentGenerator<T> cellContentGenerator;

	public GridWireframe<T> withDimension(int x, int y) {
		this.dimension = new Dimension(x, y);
		return this;
	}

	public GridWireframe<T> withContent(CellContentGenerator<T> cellContentGenerator) {
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
	public <Z extends Structurable> Wireframe<Z> transformContent(final MyRenderer.WireframeTransformer<T, Z> transformer) {
		return new GridWireframe<Z>().
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
