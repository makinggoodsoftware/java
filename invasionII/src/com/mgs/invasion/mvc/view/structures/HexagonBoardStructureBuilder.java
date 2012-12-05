package com.mgs.invasion.mvc.view.structures;

import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.structures.*;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class HexagonBoardStructureBuilder extends BaseStructureBuilder {
	private final LayeredElementStructureBuilder boardStructureBuilder;
	private final PijamaRowsStructureBuilder evenHexagonRowsLayerBuilder;
	private final PijamaRowsStructureBuilder oddHexagonRowsLayerBuilder;
	private final RectangleStructureBuilder emptyRowBuilder;
	private final VerticalSlicesStructureBuilder hexagonRowBuilder;
	private final PolygonStructureBuilder hexagonBuilder;

	public HexagonBoardStructureBuilder() {
		hexagonBuilder = PolygonStructureBuilder.polygon(new HexagonShape());
		hexagonRowBuilder = VerticalSlicesStructureBuilder.verticalSlices(hexagonBuilder);
		emptyRowBuilder = RectangleStructureBuilder.emptyRectangle();
		oddHexagonRowsLayerBuilder = PijamaRowsStructureBuilder.pijamaRows(hexagonRowBuilder, emptyRowBuilder);
		evenHexagonRowsLayerBuilder = PijamaRowsStructureBuilder.pijamaRows(hexagonRowBuilder, emptyRowBuilder);
		boardStructureBuilder = LayeredElementStructureBuilder.layered().withLayer(oddHexagonRowsLayerBuilder).withLayer(evenHexagonRowsLayerBuilder);
	}


	@Override
	protected boolean constraintsAreSatisfied() {
		return false;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		return GridFactory.empty(Wireframe.class);
	}
}
