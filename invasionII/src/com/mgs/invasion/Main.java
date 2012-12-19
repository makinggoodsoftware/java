package com.mgs.invasion;

import com.mgs.fantasi.driver.UIDriver;
import com.mgs.fantasi.profile.UIProfileFactory;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;
import com.mgs.fantasi.views.View;
import com.mgs.invasion.mvc.views.ProductionUiProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.views.LayeredElementsView.layered;
import static com.mgs.fantasi.views.RectangleView.emptyRectangle;
import static com.mgs.invasion.mvc.views.HexagonRowsView.hexagonRows;

public class Main {
	public static void main(String... args) {
//		new Main().go(new DebugUIProfileFactory());
		new Main().go(new ProductionUiProfileFactory());
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(uiProfileFactory.getUIProfile());

		int numberOVerticalDivisions = 10;
		int numberOfGenerations = 5;


		Measurement hexagonMeasurement = Measurements.futureMeasurement();
		View view =
			layered().
				withLayer(
					hexagonRows(numberOVerticalDivisions, numberOfGenerations).
						withName("OddHexagonRows").
						withHexagonMeasurement(hexagonMeasurement)
				).
				withLayer(
					emptyRectangle().
					withMargin(hexagonMeasurement.asMargin().withHalfOfItsSize()).
					withName("EvenHexagonRows").
					withContent(
							hexagonRows(numberOVerticalDivisions, numberOfGenerations).
									withOneLessColumn().
									withHexagonMeasurement(hexagonMeasurement)
					)
				);

		uiDriver.show(view, new Dimension(400, 400));
	}

}
