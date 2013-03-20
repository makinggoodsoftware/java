package com.mgs.invasion;

import com.mgs.fantasi.driver.UIDriver;
import com.mgs.fantasi.profile.UIProfile;
import com.mgs.fantasi.properties.UIProperty;
import com.mgs.fantasi.properties.UIPropertyType;
import com.mgs.fantasi.properties.data.measurements.Measurement;
import com.mgs.fantasi.properties.data.measurements.Measurements;
import com.mgs.fantasi.structure.Structure;
import com.mgs.fantasi.structure.bluePrint.BluePrint;
import com.mgs.invasion.mvc.views.HexagonRowsBuilder;
import com.mgs.invasion.mvc.views.ProfileFactory;

import javax.swing.*;
import java.awt.*;

import static com.mgs.fantasi.properties.UIPropertyFactory.uiProperty;
import static com.mgs.fantasi.structure.BluePrintBuilderFactory.createCanvas;
import static com.mgs.fantasi.structure.bluePrintPatterns.LayeredBuilder.layered;
import static com.mgs.fantasi.wireframe.Wireframes.basicRectangle;

public class Main {
	public static void main(String... args) {
		new Main().go(new ProfileFactory().debug());
	}

	private void go(UIProfile uiProfile) {
		UIDriver<JPanel> uiDriver = UIDriver.forSwing();

		int numberOVerticalDivisions = 10;
		int numberOfGenerations = 5;

		UIProperty<Measurement> hexagonMeasurement = uiProperty(Measurements.futureMeasurement(), UIPropertyType.MEASUREMENT);

		BluePrint bluePrint =
				createCanvas("main_frame").
						withFrame(basicRectangle()).
						withContent(
								layered().
										withLayer(
												createCanvas("odd_hexagons").
														withFrame(basicRectangle()).
														withContent(
																new HexagonRowsBuilder().
																		withNumberOfGenerations(numberOfGenerations).
																		withNumberOfVerticalDivisions(numberOVerticalDivisions).
																		withHexagonSize(hexagonMeasurement)
														)
										).
										withLayer(
												createCanvas("even_hexagons").
														withFrame(basicRectangle()).
														withContent(
																new HexagonRowsBuilder().
																		withNumberOfGenerations(numberOfGenerations).
																		withNumberOfVerticalDivisions(numberOVerticalDivisions).
																		withHexagonSize(hexagonMeasurement)
														)
										)
						).build();
		Structure tree = bluePrint.buildStructure();

		uiDriver.show(tree, new Dimension(400, 400), uiProfile);
	}
}
