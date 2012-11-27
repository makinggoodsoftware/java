package com.mgs.invasion.mvc.view.structures;

import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.structures.*;

public class HexagonBoardStructureBuilder extends BaseStructureBuilder {
	private final LayeredElementStructureBuilder boardStructureBuilder;
	private final PijamaRowsStructureBuilder evenHexagonRowsLayerBuilder;
	private final PijamaRowsStructureBuilder oddHexagonRowsLayerBuilder;
	private final RectangleStructureBuilder emptyRowBuilder;
	private final VerticalSlicesStructureBuilder hexagonRowBuilder;
	private final PolygonStructureBuilder hexagonBuilder;

	public HexagonBoardStructureBuilder() {
		hexagonBuilder = new PolygonStructureBuilder(new HexagonShape());
		hexagonRowBuilder = new VerticalSlicesStructureBuilder(hexagonBuilder);
		emptyRowBuilder = new RectangleStructureBuilder();
		oddHexagonRowsLayerBuilder = new PijamaRowsStructureBuilder(hexagonRowBuilder, emptyRowBuilder);
		evenHexagonRowsLayerBuilder = new PijamaRowsStructureBuilder(hexagonRowBuilder, emptyRowBuilder);
		boardStructureBuilder = new LayeredElementStructureBuilder(oddHexagonRowsLayerBuilder, evenHexagonRowsLayerBuilder);
	}


	@Override
	protected Wireframe buildContent() {
		return null;
	}
}
