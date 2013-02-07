package com.mgs.invasion;

import com.mgs.fantasi.driver.UIDriver;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.UIPropertyType;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.measurements.Measurements;
import com.mgs.fantasi.wireframe.WireframeContainer;
import com.mgs.fantasi.wireframe.WireframeContentFactory;
import com.mgs.fantasi.wireframe.builder.WireframeTreeBuilder;
import com.mgs.invasion.mvc.views.ProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.properties.UIPropertyFactory.uiProperty;
import static com.mgs.fantasi.wireframe.Wireframes.layered;
import static com.mgs.fantasi.wireframe.Wireframes.rectangle;
import static com.mgs.invasion.mvc.views.HexagonRowsWireframeTreeBuilder.hexagonRows;

public class Main {
	private final WireframeContentFactory wireframeContentFactory = new WireframeContentFactory();

	public static void main(String... args) {
		new Main().go(new ProfileFactory().debug());
	}

	private void go(UIProfile uiProfile) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing();

		int numberOVerticalDivisions = 10;
		int numberOfGenerations = 5;


		UIProperty<Measurement> hexagonMeasurement = uiProperty(Measurements.futureMeasurement(), UIPropertyType.MEASUREMENT);
		WireframeTreeBuilder wireframeTreeBuilder = layered().
				withLayer(
						hexagonRows(numberOVerticalDivisions, numberOfGenerations).
								withName("OddHexagonRows").
								withHexagonMeasurement(hexagonMeasurement)
				).
				withLayer(
						rectangle().
								withPadding(uiProperty(hexagonMeasurement.getValue().asPadding().withHalfOfItsSize(), UIPropertyType.MEASUREMENT)).
								withName("EvenHexagonRows").
								withContent(
										hexagonRows(numberOVerticalDivisions, numberOfGenerations).
												withHexagonMeasurement(hexagonMeasurement)
								)
				);
		WireframeContainer tree = wireframeTreeBuilder.build(wireframeContentFactory);

		uiDriver.show(tree, new Dimension(400, 400), uiProfile);
	}

}
