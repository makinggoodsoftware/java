package com.mgs.invasion;

import com.mgs.fantasi.polygon.HexagonShape;
import com.mgs.fantasi.structures.Fractions;
import com.mgs.fantasi.structures.PijamaRowsStructureBuilder;
import com.mgs.fantasi.structures.PolygonStructureBuilder;
import com.mgs.fantasi.ui.driver.UIDriver;
import com.mgs.fantasi.ui.profile.UIProfileFactory;
import com.mgs.fantasi.ui.wireframe.Margin;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.invasion.mvc.view.ProductionUiProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.structures.LayeredElementStructureBuilder.layered;
import static com.mgs.fantasi.structures.PijamaRowsStructureBuilder.pijamaRows;
import static com.mgs.fantasi.structures.PolygonStructureBuilder.polygon;
import static com.mgs.fantasi.structures.RectangleStructureBuilder.emptyRectangle;
import static com.mgs.fantasi.structures.VerticalSlicesStructureBuilder.verticalSlices;

public class Main {
	public static void main(String... args) {
//		new Main().go(new DebugUIProfileFactory());
		new Main().go(new ProductionUiProfileFactory());
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(uiProfileFactory.getUIProfile());

		int numberOVerticalDivisions = 10;
		int numberOfGerations = 5;

		PolygonStructureBuilder hexagon = polygon(new HexagonShape());
		PijamaRowsStructureBuilder hexagonsPijamaRows =
			pijamaRows(
				verticalSlices(hexagon).
				withVerticalDivisions(numberOVerticalDivisions)
			,
				emptyRectangle()
			).
			withFirstRowSize(Fractions.thwoThirds()).
			withNumberOfGerations(numberOfGerations);

		Wireframe content =
			layered().
				withLayer(
					hexagonsPijamaRows
				).
				withLayer(
					emptyRectangle().withMargin(new Margin(10, 10, 10, 10)).withName("2ndLayerOfHexagons")
				).
			build();

		uiDriver.show(content, new Dimension(400, 400));
	}

}
