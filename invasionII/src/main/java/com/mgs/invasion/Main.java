package com.mgs.invasion;

import com.mgs.fantasi.driver.UIDriver;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;
import com.mgs.fantasi.styles.UIProfileFactory;
import com.mgs.fantasi.views.View;
import com.mgs.invasion.mvc.views.DebugUIProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.views.LayeredElementsView.layered;
import static com.mgs.fantasi.views.RectangleView.rectangle;
import static com.mgs.invasion.mvc.views.HexagonRowsView.hexagonRows;

public class Main {
	public static void main(String... args) {
		new Main().go(new DebugUIProfileFactory());
//		new Main().go(new ProductionUiProfileFactory());
//		new Main().showEsther();
	}

	private void go(UIProfileFactory uiProfileFactory) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing();

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
								rectangle().
										withPadding(hexagonMeasurement.asPadding().withHalfOfItsSize()).
										withName("EvenHexagonRows").
										withContent(hexagonRows(numberOVerticalDivisions, numberOfGenerations).
//							withOneLessColumn().
		withHexagonMeasurement(hexagonMeasurement)
										)
						);
		uiDriver.show(view, new Dimension(400, 400), uiProfileFactory.getUIProfile());
	}

}
