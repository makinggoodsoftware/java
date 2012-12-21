package com.mgs.invasion;

import com.mgs.fantasi.driver.UIDriver;
import com.mgs.fantasi.profile.UIProfileFactory;
import com.mgs.fantasi.properties.measurements.Measurement;
import com.mgs.fantasi.properties.measurements.Measurements;
import com.mgs.fantasi.properties.polygon.HexagonShape;
import com.mgs.fantasi.properties.polygon.TriangleShape;
import com.mgs.fantasi.views.TwoLinesView;
import com.mgs.fantasi.views.View;
import com.mgs.invasion.mvc.views.DebugUIProfileFactory;
import com.mgs.invasion.mvc.views.ProductionUiProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.views.LayeredElementsView.layered;
import static com.mgs.fantasi.views.PolygonView.polygon;
import static com.mgs.fantasi.views.RectangleView.rectangle;
import static com.mgs.invasion.mvc.views.HexagonRowsView.hexagonRows;

public class Main {
	public static void main(String... args) {
		new Main().go(new DebugUIProfileFactory());
//		new Main().go(new ProductionUiProfileFactory());
//		new Main().showEsther();
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
					rectangle().
						withPadding(hexagonMeasurement.asPadding().withHalfOfItsSize()).
						withName("EvenHexagonRows").
						withContent(hexagonRows(numberOVerticalDivisions, numberOfGenerations).
							withOneLessColumn().
							withHexagonMeasurement(hexagonMeasurement)
				)
			);

		uiDriver.show(view, new Dimension(400, 400));
	}

	public void showEsther(){
		UIDriver<JPanel> uiDriver = UIDriver.forSwing(new ProductionUiProfileFactory().getUIProfile());

		View diffferentSizePolygons = new TwoLinesView(
				polygon(new TriangleShape()),
				polygon(new HexagonShape())
		);

		uiDriver.show(diffferentSizePolygons, new Dimension(400, 400));
	}
}
