package com.mgs.invasion;

import com.mgs.fantasi.measurements.Measurement;
import com.mgs.fantasi.measurements.Measurements;
import com.mgs.fantasi.ui.driver.UIDriver;
import com.mgs.fantasi.ui.profile.UIProfileFactory;
import com.mgs.fantasi.views.HexagonRowsView;
import com.mgs.fantasi.views.View;
import com.mgs.invasion.mvc.view.ProductionUiProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.views.LayeredElementsView.layered;
import static com.mgs.fantasi.views.RectangleView.emptyRectangle;

public class Main {
	public static void main(String... args) {
//		new Main().go(new DebugUIProfileFactory());
		new Main().go(new ProductionUiProfileFactory());
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(uiProfileFactory.getUIProfile());

		int numberOVerticalDivisions = 10;
		int numberOfGenerations = 5;


		HexagonRowsView hexagonRows = HexagonRowsView.newHexagonRows(numberOVerticalDivisions, numberOfGenerations);

		Measurement hexagonMeasurement = Measurements.futureMeasurement();
		View view =
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
				);

		uiDriver.show(view, new Dimension(400, 400));
	}

}
