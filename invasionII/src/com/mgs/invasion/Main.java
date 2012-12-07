package com.mgs.invasion;

import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.measurements.Measurements;
import com.mgs.fantasi.structures.HexagonRowsStructureBuilder;
import com.mgs.fantasi.ui.driver.UIDriver;
import com.mgs.fantasi.ui.profile.UIProfileFactory;
import com.mgs.fantasi.ui.wireframe.Wireframe;
import com.mgs.invasion.mvc.view.ProductionUiProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.structures.LayeredElementStructureBuilder.layered;
import static com.mgs.fantasi.structures.RectangleStructureBuilder.emptyRectangle;

public class Main {
	public static void main(String... args) {
//		new Main().go(new DebugUIProfileFactory());
		new Main().go(new ProductionUiProfileFactory());
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(uiProfileFactory.getUIProfile());

		int numberOVerticalDivisions = 10;
		int numberOfGerations = 5;


		HexagonRowsStructureBuilder hexagonRows = HexagonRowsStructureBuilder.newHexagonRows(numberOVerticalDivisions, numberOfGerations);

		Measurement hexagonMeasurement = Measurements.futureMeasurement ();
		Wireframe board =
			layered().
				withLayer(
					hexagonRows.
						withName("OddHexagonRows").
						withHexagonMeasurement(hexagonMeasurement)
				).
				withLayer(
					emptyRectangle().
					withMargin(hexagonMeasurement.asMargin().withHalfOfItsSize()).
					withName("EvenHexagonRows").
					withContent(
							hexagonRows.newCopy().
									withOneLessColumn().
									withHexagonMeasurement(hexagonMeasurement)
					)
				).
			build();

		uiDriver.show(board, new Dimension(400, 400));
	}

}
