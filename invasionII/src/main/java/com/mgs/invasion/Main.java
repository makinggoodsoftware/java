package com.mgs.invasion;

import com.mgs.fantasi.driver.UIDriver;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.UIPropertyType;
import com.mgs.fantasi.properties.data.Padding;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.measurements.Measurements;
import com.mgs.fantasi.wireframe.tree.WireframeTree;
import com.mgs.fantasi.wireframe.tree.builder.WireframeTreeBuilder;
import com.mgs.invasion.mvc.views.ProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.properties.UIPropertiesBuilderFactory.rectangularEmpty;
import static com.mgs.fantasi.properties.UIPropertyFactory.uiProperty;
import static com.mgs.fantasi.wireframe.Wireframes.layered;
import static com.mgs.invasion.mvc.views.HexagonRowsWireframeTreeBuilder.hexagonRows;

public class Main {
	public static void main(String... args) {
		new Main().go(new ProfileFactory().debug());
	}

	private void go(UIProfile uiProfile) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing();

		int numberOVerticalDivisions = 10;
		int numberOfGenerations = 5;

		UIProperty<Measurement> hexagonMeasurement = uiProperty(Measurements.futureMeasurement(), UIPropertyType.MEASUREMENT);
		Padding margin = hexagonMeasurement.getValue().asPadding().withHalfOfItsSize();

		WireframeTreeBuilder wireframeTreeBuilder = layered("main_frame", rectangularEmpty())
				.withLayer(
						hexagonRows("odd_hexagons", rectangularEmpty())
								.withNumberOfGenerations(numberOfGenerations)
								.withNumberOfVerticalDivisions(numberOVerticalDivisions)
								.withHexagonSize(hexagonMeasurement)
				).
						withLayer(
								hexagonRows("even_hexagons", rectangularEmpty().withMargin(uiProperty(margin, UIPropertyType.MEASUREMENT)))
										.withNumberOfGenerations(numberOfGenerations)
										.withNumberOfVerticalDivisions(numberOVerticalDivisions)
										.withHexagonSize(hexagonMeasurement)
						);
		WireframeTree tree = wireframeTreeBuilder.build();

		uiDriver.show(tree, new Dimension(400, 400), uiProfile);
	}

}
