package com.mgs.invasion;

import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.structures.HexagonRowsStructureBuilder;
import com.mgs.fantasi.ui.driver.UIDriver;
import com.mgs.fantasi.ui.profile.BorderDefinition;
import com.mgs.fantasi.ui.profile.UIProfileFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.invasion.mvc.view.ProductionUiProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.measurements.Measurements.unit;
import static com.mgs.fantasi.structures.LayeredElementStructureBuilder.layered;
import static com.mgs.fantasi.structures.RectangleStructureBuilder.emptyRectangle;
import static com.mgs.fantasi.structures.VerticalSlicesStructureBuilder.verticalSlices;

public class Main {
	public static void main(String... args) {
//		new Main().go(new DebugUIProfileFactory());
		new Main().goTest(new ProductionUiProfileFactory());
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(uiProfileFactory.getUIProfile());

		int numberOVerticalDivisions = 10;
		int numberOfGerations = 5;

		Measurement hexagonSize = unit();

		HexagonRowsStructureBuilder hexagonRows = HexagonRowsStructureBuilder.newHexagonRows(numberOVerticalDivisions, numberOfGerations).
				withHexagonSize(hexagonSize);

		Wireframe board =
			layered().
				withLayer(
					hexagonRows.
						withName("OddHexagonRows")
				).
				withLayer(
					emptyRectangle().
						withMargin(hexagonSize.asMargin().withHalfOfItsSize()).
						withName("EvenHexagonRows").
						withContent(
							hexagonRows.newCopy().withOneLessColumn()
						)
			).
			build();

		uiDriver.show(board, new Dimension(400, 400));
	}
	
	private void goTest(UIProfileFactory uiProfileFactory){
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(uiProfileFactory.getUIProfile());
		
		Wireframe toTest =
			verticalSlices
			(
				emptyRectangle().withSize(unit()).withBorder(new BorderDefinition(Color.RED, 1))
			).
			withBorder(new BorderDefinition(Color.BLACK, 10)).
			withVerticalDivisions(6).
			packed().
		build();

		uiDriver.show(toTest, new Dimension(658, 650));
	}
}
