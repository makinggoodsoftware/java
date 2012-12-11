package com.mgs.invasion.mvc.view.structures;

import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.views.*;
import com.mgs.fantasi.ui.wireframe.GridFactory;
import com.mgs.fantasi.ui.wireframe.Structure;
import com.mgs.fantasi.ui.wireframe.Wireframe;

public class HexagonBoardView extends BaseView {
	private final LayeredElementsView boardStructureBuilder;
	private final PijamaRowsView evenHexagonRowsLayerBuilder;
	private final PijamaRowsView oddHexagonRowsLayerBuilder;
	private final RectangleView emptyRowBuilder;
	private final VerticalSlicesView hexagonRowBuilder;
	private final PolygonView hexagonBuilder;

	public HexagonBoardView() {
		hexagonBuilder = PolygonView.polygon(new HexagonShape());
		hexagonRowBuilder = VerticalSlicesView.verticalSlices(hexagonBuilder);
		emptyRowBuilder = RectangleView.emptyRectangle();
		oddHexagonRowsLayerBuilder = PijamaRowsView.pijamaRows(hexagonRowBuilder, emptyRowBuilder);
		evenHexagonRowsLayerBuilder = PijamaRowsView.pijamaRows(hexagonRowBuilder, emptyRowBuilder);
		boardStructureBuilder = LayeredElementsView.layered().withLayer(oddHexagonRowsLayerBuilder).withLayer(evenHexagonRowsLayerBuilder);
	}


	@Override
	protected boolean constraintsAreSatisfied() {
		return false;
	}

	@Override
	protected Structure buildLayoutAndChilds() {
		return GridFactory.empty(Wireframe.class);
	}

	@Override
	protected BaseView copy() {
		return null;
	}
}
