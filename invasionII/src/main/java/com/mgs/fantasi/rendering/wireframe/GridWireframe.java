package com.mgs.fantasi.rendering.wireframe;

import com.mgs.fantasi.Structurable;
import com.mgs.fantasi.rendering.ViewPreprocessorImpl;
import com.mgs.fantasi.rendering.structure.StructureType;
import com.mgs.fantasi.rendering.structure.grid.GridStructure;
import com.mgs.fantasi.rendering.structure.Structure;
import com.mgs.fantasi.rendering.structure.grid.*;
import com.mgs.fantasi.rendering.structure.grid.ArrayListGridStructure;

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
		GridStructure<T> viewGridStructure = new ArrayListGridStructure<T>(dimension.width, dimension.height);
		viewGridStructure.fillCells(cellContentGenerator);
		return viewGridStructure;
	}

	@Override
	public <Z extends Structurable> Wireframe<Z> transform(final ViewPreprocessorImpl.WireframeTransformer<T, Z> transformer) {
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

	@Override
	public StructureType getType() {
		return StructureType.GRID;
	}
}
